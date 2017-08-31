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

import fr.paris.lutece.plugins.elasticdata.business.DataObject;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;
import java.util.ArrayList;
import java.util.Collection;

/**
 * SignalementDAO
 */
public class SignalementDAO
{

    private static final String SQL_QUERY_SELECTALL = "SELECT id_signalement, "
            + " CONCAT(prefix, annee, mois, numero) as \"numero_anomalie\" "
            + " prefix as canal, ws.name as statut,  ST_X(geom) as \"lon\", ST_Y(geom) as \"lat\", "
            + " stsa.alias_mobile as \"description_public\", "
            + " (substring((date_creation ||'') from 0 for 11) || ' ' || substring((heure_creation || '') from 12 for 8)) as \"date_creation\", "
            + " ts.libelle as \"categorie\", tss.libelle as \"categorie_parent\", tsss.libelle as \"categorie_grandparent\" "
            + " FROM signalement_signalement s "
            + " INNER JOIN signalement_adresse a ON s.id_signalement = a.fk_id_signalement "
            + " INNER JOIN signalement_type_signalement ts ON ts.id_type_signalement = s.fk_id_type_signalement "
            + " LEFT JOIN signalement_type_signalement tss ON ts.fk_id_type_signalement = tss.id_type_signalement "
            + " LEFT JOIN signalement_type_signalement tsss ON tss.fk_id_type_signalement = tsss.id_type_signalement "
            + " LEFT JOIN signalement_type_signalement_alias stsa ON stsa.fk_id_type_signalement = s.fk_id_type_signalement "
            + " LEFT JOIN workflow_resource_workflow wrw ON  s.id_signalement=wrw.id_resource AND wrw.resource_type='SIGNALEMENT_SIGNALEMENT' "
            + " INNER JOIN workflow_state ws ON wrw.id_state=ws.id_state ORDER BY id_signalement ASC ) ";

    public Collection<DataObject> selectSignalementDataObjectsList( Plugin plugin )
    {
        Collection<DataObject> listSignalementDataObjects = new ArrayList<>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            SignalementDataObject signalement = new SignalementDataObject( );

            signalement.setId( daoUtil.getInt( "id_signalement" ) );
            signalement.setNumeroAnomalie( daoUtil.getString( "numero_anomalie" ) );
            signalement.setCanal( daoUtil.getString( "canal" ));
            signalement.setStatut( daoUtil.getString( "statut" ));
            signalement.setDescriptionPublic( "description_public" );
            Location location = new Location();
            location.setLon( daoUtil.getString( "lon" ) );
            location.setLat( daoUtil.getString( "lat" ) );
            signalement.setLocation( location );
            signalement.setDateCreation( daoUtil.getDate( "date_creation" ) );
            signalement.setCategory( daoUtil.getString( "categorie" ) );

            listSignalementDataObjects.add( signalement );
        }

        daoUtil.free( );

        return listSignalementDataObjects;
    }

}
