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

import java.sql.Date;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.paris.lutece.plugins.elasticdata.business.DataObject;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

/**
 * SignalementDAO
 */
public class SignalementDAO
{

    private static final String SQL_QUERY_SELECTALL_ID_SIGNALEMENT = "SELECT id_signalement FROM signalement_export";

    private static final String SQL_QUERY_SELECTALL = "SELECT ss.id_signalement, se.numero, priorite, type_signalement, alias, alias_mobile, direction, quartier, adresse, coord_x, coord_y, "
            + " arrondissement, secteur, se.date_creation, heure_creation, etat, mail_usager, commentaire_usager, nb_photos, raisons_rejet, "
            + " nb_suivis, nb_felicitations, se.date_cloture, is_photo_service_fait, mail_destinataire_courriel, se.courriel_expediteur, date_envoi_courriel, "
            + " id_mail_service_fait, executeur_service_fait, se.date_derniere_action, date_prevu_traitement, se.commentaire_agent_terrain, executeur_rejet, "
            + " executeur_mise_surveillance, nb_requalifications, to_char(ss.service_fait_date_passage,'HH24:MI') heure_sf, executeur_requalification, executeur_requalification_bis, premier_id_type_signalement, premier_direction, se.precision_terrain, "
            + " TO_CHAR(ss.date_creation, 'dd/mm/yyyy ; HH24:MI:SS') as ts_creation, "
            + " TO_CHAR(dateHistory.date_cloture, 'dd/mm/yyyy ; HH24:MI:SS') as ts_date_closure, "
            + " TO_CHAR(lastAction.date_derniere_action, 'dd/mm/yyyy ; HH24:MI:SS') as ts_date_derniere_action, "
            + " TO_CHAR(ss.date_prevue_traitement, 'dd/mm/yyyy ; HH24:MI:SS') as ts_prevue_traitement, "
            + " TO_CHAR(ss.courriel_date, 'dd/mm/yyyy ; HH24:MI:SS') as ts_courriel_date, "
            + " TO_CHAR(ss.service_fait_date_passage, 'dd/mm/yyyy ; HH24:MI:SS') as ts_service_fait_date_passage "
            + " FROM signalement_export se join signalement_signalement ss on ss.id_signalement = se.id_signalement "
            + " left join (select id_resource , max(creation_date) as date_derniere_action from workflow_resource_history group by id_resource) as lastAction on ss.id_signalement = lastAction.id_resource"
            + " left join  (select ss.id_signalement, case when id_state=10 THEN  ss.service_fait_date_passage when id_state=22 THEN  ss.date_mise_surveillance when id_state=11 THEN  ss.date_rejet end date_cloture from signalement_signalement ss left join workflow_resource_history wrh on ss.id_signalement = wrh.id_resource left join workflow_resource_workflow wrw on ss.id_signalement = wrw.id_resource ) as dateHistory on ss.id_signalement =  dateHistory.id_signalement ";

    private static final String SQL_QUERY_SELECTALL_WHERE_CLAUSE =" WHERE se.id_signalement IN ({0})";

    /**
     * Select signalement for export ElasticSearch
     * @param plugin
     * @param lastIndexation
     *          last Indexation date
     * @param idsSignalementToIndex
     *          list id signalement to index
     * @return Collection to send to ElasticSearch
     */
    public List<DataObject> selectSignalementDataObjectsList( Plugin plugin, Date lastIndexation, List<String> idsSignalementToIndex )
    {
        List<DataObject> listSignalementDataObjects = new ArrayList<>( );

        StringBuilder sbSQL = new StringBuilder( SQL_QUERY_SELECTALL );
        sbSQL.append( MessageFormat.format( SQL_QUERY_SELECTALL_WHERE_CLAUSE, String.join( ",", idsSignalementToIndex ) ) );

        try ( DAOUtil daoUtil = new DAOUtil( sbSQL.toString( ), plugin ) )
        {

            daoUtil.executeQuery( );

            while ( daoUtil.next( ) )
            {
                SignalementDataObject signalement = new SignalementDataObject( );

                signalement.setId( String.valueOf(  daoUtil.getLong( 1 )) );
                signalement.setDocumentTypeName( "Signalement" );

                signalement.setIdSignalement( daoUtil.getLong( 1 ) );
                signalement.setNumero( daoUtil.getString( 2 ) );
                signalement.setPriorite( daoUtil.getString( 3 ) );
                signalement.setTypeSignalement( daoUtil.getString( 4 ) );
                signalement.setAlias( daoUtil.getString( 5 ) );
                signalement.setAliasMobile( daoUtil.getString( 6 ) );
                signalement.setDirection( daoUtil.getString( 7 ) );
                signalement.setQuartier( daoUtil.getString( 8 ) );
                signalement.setAdresse( daoUtil.getString( 9 ) );
                signalement.setCoordX( daoUtil.getDouble( 10 ) );
                signalement.setCoordY( daoUtil.getDouble( 11 ) );
                signalement.setArrondissement( daoUtil.getString( 12 ) );
                signalement.setSecteur( daoUtil.getString( 13 ) );
                signalement.setDateCeration( daoUtil.getString( 14 ) );
                signalement.setHeureCeration( daoUtil.getString( 15 ) );
                signalement.setEtat( daoUtil.getString( 16 ) );
                signalement.setMailUsager( daoUtil.getString( 17 ) );
                signalement.setCommentaireUsager( daoUtil.getString( 18 ) );
                signalement.setNbPhotos( daoUtil.getInt( 19 ) );
                signalement.setRaisonRejet( daoUtil.getString( 20 ) );
                signalement.setNbSuivis( daoUtil.getInt( 21 ) );
                signalement.setNbFelicitations( daoUtil.getInt( 22 ) );
                signalement.setDateCloture( daoUtil.getString( 23 ) );
                signalement.setIsPhotoServiceFait( daoUtil.getInt( 24 ) );
                signalement.setMailDestinataireCourriel( daoUtil.getString( 25 ) );
                signalement.setCourrielExpediteur( daoUtil.getString( 26 ) );
                signalement.setDateEnvoiCourriel( daoUtil.getString( 27 ) );
                signalement.setIdMailServiceFait( daoUtil.getInt( 28 ) );
                signalement.setExecuteurServiceFait( daoUtil.getString( 29 ) );
                signalement.setDateDerniereAction( daoUtil.getString( 30 ) );
                signalement.setDatePrevuTraitement( daoUtil.getString( 31 ) );
                signalement.setCommentaireAgentTerrain( daoUtil.getString( 32 ) );
                signalement.setExecuteurRejet( daoUtil.getString( 33 ) );
                signalement.setExecuteurMiseEnSurvreillance( daoUtil.getString( 34 ) );
                signalement.setNbRequalifications( daoUtil.getInt( 35 ) );
                signalement.setHeureServiceFait( daoUtil.getString( 36 ) );
                signalement.setExecuteurPremiereRequalification( daoUtil.getString( 37 ) );
                signalement.setExecuteurSecondeRequalification( daoUtil.getString( 38 ) );
                signalement.setPremierIdTypeSignalement( daoUtil.getInt( 39 ) );
                signalement.setPremiereDirection( daoUtil.getString( 40 ) );
                signalement.setPrecisionTerrain( daoUtil.getString( 41 ) );

                signalement.setColonneVide1( StringUtils.EMPTY );
                signalement.setColonneVide2( StringUtils.EMPTY );
                signalement.setColonneVide3( StringUtils.EMPTY );
                signalement.setColonneVide4( StringUtils.EMPTY );
                signalement.setColonneVide5( StringUtils.EMPTY );

                listSignalementDataObjects.add( signalement );
            }

        }

        return listSignalementDataObjects;
    }

    /**
     * Select all ids Signalement for full Indexing daemon
     * @param plugin
     * @return List of all id signalement
     */
    public List<String> selectFullIdsSignalement (Plugin plugin) {

        List<String> listIdsSignalement = new ArrayList<>( );
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID_SIGNALEMENT, plugin ) )
        {
            daoUtil.executeQuery( );

            while ( daoUtil.next( ) )
            {
                listIdsSignalement.add( String.valueOf(  daoUtil.getLong( 1 )) );
            }
        }

        return listIdsSignalement;
    }

}
