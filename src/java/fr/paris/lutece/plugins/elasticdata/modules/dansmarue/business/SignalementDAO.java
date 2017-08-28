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

    private static final String SQL_QUERY_SELECTALL = "SELECT id_signalement, date_creation, heure_creation, date_prevue_traitement, ST_AsText(geom), ts.libelle, tss.libelle as libelleparent, tsss.libelle as libellegrandparent "
            + " FROM signalement_signalement s "
            + " INNER JOIN signalement_adresse a ON s.id_signalement = a.fk_id_signalement "
            + " INNER JOIN signalement_type_signalement ts ON ts.id_type_signalement = s.fk_id_type_signalement "
            + " LEFT JOIN signalement_type_signalement tss ON ts.fk_id_type_signalement = tss.id_type_signalement "
            + " LEFT JOIN signalement_type_signalement tsss ON tss.fk_id_type_signalement = tsss.id_type_signalement;";

    public Collection<DataObject> selectSignalementDataObjectsList( Plugin plugin )
    {
        Collection<DataObject> listSignalementDataObjects = new ArrayList<>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            SignalementDataObject signalementDataObject = new SignalementDataObject( );

            signalementDataObject.setId( daoUtil.getInt( 1 ) );
            signalementDataObject.setDateCreation( daoUtil.getDate( 2 ) );
            signalementDataObject.setDatePrevueTraitement( daoUtil.getDate( 3 ) );
            signalementDataObject.setLocation( daoUtil.getString( 4 ) );
            signalementDataObject.setCategory( daoUtil.getString( 5 ) );

            listSignalementDataObjects.add( signalementDataObject );
        }

        daoUtil.free( );

        return listSignalementDataObjects;
    }

}
