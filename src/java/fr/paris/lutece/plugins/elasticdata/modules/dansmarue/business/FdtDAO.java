/*
 * Copyright (c) 2002-2017, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.elasticdata.modules.dansmarue.business;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.paris.lutece.plugins.elasticdata.business.DataObject;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

/**
 * FdtDAO
 */
public class FdtDAO
{

    private static final String SQL_QUERY_SELECTALL_ID_FDT       = "SELECT id FROM signalement_feuille_de_tournee";

    private static final String SQL_QUERY_SELECTALL              = "SELECT fdt.id,fdt.nom,fdt.createur, to_char(fdt.date_creation, 'dd/MM/YYYY') as date_creation,  to_char(fdt.date_modification, 'dd/MM/YYYY') as date_modification, uu.\"label\" as direction, uu2.\"label\" as entite,  array_to_string(array_agg(se.numero),',') as ids_fonct, array_to_string(signalement_ids, ',') as ids_tech, fdt.nb_consultation"
            + " FROM signalement_feuille_de_tournee fdt left join unittree_unit uu on uu.id_unit = fdt.id_direction left join unittree_unit uu2 on uu2.id_unit = fdt.id_entite left join signalement_export se on se.id_signalement = any (fdt.signalement_ids::int[])";

    private static final String SQL_QUERY_SELECTALL_WHERE_CLAUSE = " WHERE fdt.id IN ({0})";

    private static final String GROUP_BY_CLAUSE = " GROUP BY fdt.id,uu.\"label\",uu2.\"label\"";

    /**
     * Select Fdt for export ElasticSearch
     *
     * @param plugin
     * @param idsFdtToIndex
     *            list id fdt to index
     * @return Collection to send to ElasticSearch
     */
    public List<DataObject> selectFdtDataObjectsList( Plugin plugin, List<String> idsFdtToIndex )
    {
        List<DataObject> listFdtDataObjects = new ArrayList<>( );

        StringBuilder sbSQL = new StringBuilder( SQL_QUERY_SELECTALL );
        sbSQL.append( MessageFormat.format( SQL_QUERY_SELECTALL_WHERE_CLAUSE, String.join( ",", idsFdtToIndex ) ) );
        sbSQL.append( GROUP_BY_CLAUSE );

        try ( DAOUtil daoUtil = new DAOUtil( sbSQL.toString( ), plugin ) )
        {

            daoUtil.executeQuery( );

            while ( daoUtil.next( ) )
            {
                FdtDataObject fdtObject = new FdtDataObject( );

                fdtObject.setId( String.valueOf( daoUtil.getLong( 1 ) ) );
                fdtObject.setDocumentTypeName( "FeuilleDeTournee" );

                fdtObject.setIdFdt( daoUtil.getLong( 1 ) );
                fdtObject.setNom( daoUtil.getString( 2 ) );
                fdtObject.setCreateur( daoUtil.getString( 3 ) );
                fdtObject.setDateCreation( daoUtil.getString( 4 ) );
                fdtObject.setDateModification( daoUtil.getString( 5 ) );
                fdtObject.setDirection( daoUtil.getString( 6 ) );
                fdtObject.setEntite( daoUtil.getString( 7 ) );

                String numerosSignalement = daoUtil.getString( 8 );
                List<String> listNumeroSignalement = Arrays.asList( numerosSignalement.split( "," ) );
                fdtObject.setListSignalementNumeros( listNumeroSignalement.stream( ).collect( Collectors.toList( ) ) );

                String idsSignalement = daoUtil.getString( 9 );
                List<String> listIdSignalement = Arrays.asList( idsSignalement.split( "," ) );
                fdtObject.setListSignalementIds( listIdSignalement.stream( ).map( Integer::valueOf ).collect( Collectors.toList( ) ) );

                fdtObject.setNbCosultation( daoUtil.getInt( 10 ) );

                listFdtDataObjects.add( fdtObject );
            }

        }

        return listFdtDataObjects;
    }

    /**
     * Select all ids Fdt for full Indexing daemon
     *
     * @param plugin
     * @return List of all id fdt
     */
    public List<String> selectFullIdsFdt( Plugin plugin )
    {

        List<String> listIdsFdt = new ArrayList<>( );
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID_FDT, plugin ) )
        {
            daoUtil.executeQuery( );

            while ( daoUtil.next( ) )
            {
                listIdsFdt.add( String.valueOf( daoUtil.getLong( 1 ) ) );
            }
        }

        return listIdsFdt;
    }

}
