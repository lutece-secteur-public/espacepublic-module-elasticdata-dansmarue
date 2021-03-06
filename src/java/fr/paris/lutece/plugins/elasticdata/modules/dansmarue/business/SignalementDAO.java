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
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.elasticdata.business.DataObject;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

/**
 * SignalementDAO
 */
public class SignalementDAO
{

    private static final String SQL_QUERY_SELECTALL = "SELECT numero, priorite, type_signalement, alias, alias_mobile, direction, quartier, adresse, coord_x, coord_y, "
            + "arrondissement, secteur, date_creation, heure_creation, etat, mail_usager, commentaire_usager, nb_photos, raisons_rejet, "
            + "nb_suivis, nb_felicitations, date_cloture, is_photo_service_fait, mail_destinataire_courriel, courriel_expediteur, date_envoi_courriel, "
            + "id_mail_service_fait, executeur_service_fait, date_derniere_action, date_prevu_traitement, commentaire_agent_terrain, executeur_rejet, "
            + "executeur_mise_surveillance, nb_requalifications " + "FROM signalement_export WHERE to_date(date_creation, 'DD/MM/YYYY') >= ? OR to_date(date_derniere_action, 'DD/MM/YYYY') >= ?;";

    /**
     * Select signalement for export ElasticSearch
     * @param plugin
     * @param lastIndexation
     *          last Indexation date
     * @return Collection to send to ElasticSearch
     */
    public Collection<DataObject> selectSignalementDataObjectsList( Plugin plugin, Date lastIndexation )
    {
        Collection<DataObject> listSignalementDataObjects = new ArrayList<>( );
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin ) )
        {

            daoUtil.setDate( 1, lastIndexation );
            daoUtil.setDate( 2, lastIndexation );

            daoUtil.executeQuery( );

            while ( daoUtil.next( ) )
            {
                SignalementDataObject signalement = new SignalementDataObject( );

                signalement.setNumero( daoUtil.getString( 1 ) );
                signalement.setPriorite( daoUtil.getString( 2 ) );
                signalement.setTypeSignalement( daoUtil.getString( 3 ) );
                signalement.setAlias( daoUtil.getString( 4 ) );
                signalement.setAliasMobile( daoUtil.getString( 5 ) );
                signalement.setDirection( daoUtil.getString( 6 ) );
                signalement.setQuartier( daoUtil.getString( 7 ) );
                signalement.setAdresse( daoUtil.getString( 8 ) );
                signalement.setCoordX( daoUtil.getDouble( 9 ) );
                signalement.setCoordY( daoUtil.getDouble( 10 ) );
                signalement.setArrondissement( daoUtil.getString( 11 ) );
                signalement.setSecteur( daoUtil.getString( 12 ) );
                signalement.setDateCeration( daoUtil.getString( 13 ) );
                signalement.setHeureCeration( daoUtil.getString( 14 ) );
                signalement.setEtat( daoUtil.getString( 15 ) );
                signalement.setMailUsager( daoUtil.getString( 16 ) );
                signalement.setCommentaireUsager( daoUtil.getString( 17 ) );
                signalement.setNbPhotos( daoUtil.getInt( 18 ) );
                signalement.setRaisonRejet( daoUtil.getString( 19 ) );
                signalement.setNbSuivis( daoUtil.getInt( 20 ) );
                signalement.setNbFelicitations( daoUtil.getInt( 21 ) );
                signalement.setDateCloture( daoUtil.getString( 22 ) );
                signalement.setIsPhotoServiceFait( daoUtil.getInt( 23 ) );
                signalement.setMailDestinataireCourriel( daoUtil.getString( 24 ) );
                signalement.setCourrielExpediteur( daoUtil.getString( 25 ) );
                signalement.setDateEnvoiCourriel( daoUtil.getString( 26 ) );
                signalement.setIdMailServiceFait( daoUtil.getInt( 27 ) );
                signalement.setExecuteurServiceFait( daoUtil.getString( 28 ) );
                signalement.setDateDerniereAction( daoUtil.getString( 29 ) );
                signalement.setDatePrevuTraitement( daoUtil.getString( 30 ) );
                signalement.setCommentaireAgentTerrain( daoUtil.getString( 31 ) );
                signalement.setExecuteurRejet( daoUtil.getString( 32 ) );
                signalement.setExecuteurMiseEnSurvreillance( daoUtil.getString( 33 ) );
                signalement.setNbRequalifications( daoUtil.getInt( 34 ) );

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

}
