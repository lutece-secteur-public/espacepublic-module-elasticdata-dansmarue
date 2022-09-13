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

import fr.paris.lutece.plugins.elasticdata.business.AbstractDataObject;

/**
 * SignalementDataObject
 */
public class SignalementDataObject extends AbstractDataObject
{

    // Variables declarations
    private long   _nIdSignalement;
    private String _strNumero;
    private String _strPriorite;
    private String _strTypeSignalement;
    private String _strAlias;
    private String _strAliasMobile;
    private String _strDirection;
    private String _strQuartier;
    private String _strAdresse;
    private Double _nCoordX;
    private Double _nCoordY;
    private String _strArrondissement;
    private String _strSecteur;
    private String _strDateCeration;
    private String _strHeureCeration;
    private String _strEtat;
    private String _strMailUsager;
    private String _strCommentaireUsager;
    private int    _nNbPhotos;
    private String _strRaisonRejet;
    private int    _nNbSuivis;
    private int    _nNbFelicitations;
    private String _strDateCloture;
    private int    _nIsPhotoServiceFait;
    private String _strMailDestinataireCourriel;
    private String _strCourrielExpediteur;
    private String _strDateEnvoiCourriel;
    private int    _nIdMailServiceFait;
    private String _strExecuteurServiceFait;
    private String _strDateDerniereAction;
    private String _strDatePrevuTraitement;
    private String _strCommentaireAgentTerrain;
    private String _strExecuteurRejet;
    private String _strExecuteurMiseEnSurvreillance;
    private int    _nNbRequalifications;
    private String _strHeureServiceFait;

    // DMR-1994 5 colonnes vides pour l'implémentation dans Digdash
    private String _strColonneVide1 = "";
    private String _strColonneVide2 = "";
    private String _strColonneVide3 = "";
    private String _strColonneVide4 = "";
    private String _strColonneVide5 = "";




    /**
     *
     * @return _nIdSignalement
     */
    public long getIdSignalement( )
    {
        return _nIdSignalement;
    }


    /**
     *
     * @return _strNumero
     */
    public String getNumero( )
    {
        return _strNumero;
    }

    /**
     *
     * @return _strPriorite
     */
    public String getPriorite( )
    {
        return _strPriorite;
    }

    /**
     *
     * @return _strTypeSignalement
     */
    public String getTypeSignalement( )
    {
        return _strTypeSignalement;
    }

    /**
     *
     * @return _strAlias
     */
    public String getAlias( )
    {
        return _strAlias;
    }

    /**
     *
     * @return _strDirection
     */
    public String getDirection( )
    {
        return _strDirection;
    }

    /**
     *
     * @return _strQuartier
     */
    public String getQuartier( )
    {
        return _strQuartier;
    }

    /**
     *
     * @return _strAdresse
     */
    public String getAdresse( )
    {
        return _strAdresse;
    }

    /**
     *
     * @return _nCoordX
     */
    public Double getCoordX( )
    {
        return _nCoordX;
    }

    /**
     *
     * @return _nCoordY
     */
    public Double getCoordY( )
    {
        return _nCoordY;
    }

    /**
     *
     * @return _strArrondissement
     */
    public String getArrondissement( )
    {
        return _strArrondissement;
    }

    /**
     *
     * @return _strSecteur
     */
    public String getSecteur( )
    {
        return _strSecteur;
    }

    /**
     *
     * @return _strDateCeration
     */
    public String getDateCeration( )
    {
        return _strDateCeration;
    }

    /**
     *
     * @return _strHeureCeration
     */
    public String getHeureCeration( )
    {
        return _strHeureCeration;
    }

    /**
     *
     * @return _strEtat
     */
    public String getEtat( )
    {
        return _strEtat;
    }

    /**
     *
     * @return _strMailUsager
     */
    public String getMailUsager( )
    {
        return _strMailUsager;
    }

    /**
     *
     * @return _strCommentaireUsager
     */
    public String getCommentaireUsager( )
    {
        return _strCommentaireUsager;
    }

    /**
     *
     * @return _nNbPhotos
     */
    public int getNbPhotos( )
    {
        return _nNbPhotos;
    }

    /**
     *
     * @return _strRaisonRejet
     */
    public String getRaisonRejet( )
    {
        return _strRaisonRejet;
    }

    /**
     *
     * @return _nNbSuivis
     */
    public int getNbSuivis( )
    {
        return _nNbSuivis;
    }

    /**
     *
     * @return _nNbFelicitations
     */
    public int getNbFelicitations( )
    {
        return _nNbFelicitations;
    }

    /**
     *
     * @return _strDateCloture
     */
    public String getDateCloture( )
    {
        return _strDateCloture;
    }

    /**
     *
     * @return _nIsPhotoServiceFait
     */
    public int getIsPhotoServiceFait( )
    {

        return _nIsPhotoServiceFait;
    }

    /**
     *
     * @return _strMailDestinataireCourriel
     */
    public String getMailDestinataireCourriel( )
    {
        return _strMailDestinataireCourriel;
    }

    /**
     *
     * @return _strCourrielExpediteur
     */
    public String getCourrielExpediteur( )
    {
        return _strCourrielExpediteur;
    }

    /**
     *
     * @return _strDateEnvoiCourriel
     */
    public String getDateEnvoiCourriel( )
    {
        return _strDateEnvoiCourriel;
    }

    /**
     *
     * @return _nIdMailServiceFait
     */
    public int getIdMailServiceFait( )
    {
        return _nIdMailServiceFait;
    }

    /**
     *
     * @return _strExecuteurServiceFait
     */
    public String getExecuteurServiceFait( )
    {
        return _strExecuteurServiceFait;
    }

    /**
     *
     * @return _strDateDerniereAction
     */
    public String getDateDerniereAction( )
    {
        return _strDateDerniereAction;
    }

    /**
     *
     * @return _strDatePrevuTraitement
     */
    public String getDatePrevuTraitement( )
    {
        return _strDatePrevuTraitement;
    }

    /**
     *
     * @return _strCommentaireAgentTerrain
     */
    public String getCommentaireAgentTerrain( )
    {
        return _strCommentaireAgentTerrain;
    }

    /**
     *
     * @return _strExecuteurRejet
     */
    public String getExecuteurRejet( )
    {
        return _strExecuteurRejet;
    }

    /**
     *
     * @return _strExecuteurMiseEnSurvreillance
     */
    public String getExecuteurMiseEnSurvreillance( )
    {
        return _strExecuteurMiseEnSurvreillance;
    }

    /**
     *
     * @return _nNbRequalifications
     */
    public int getNbRequalifications( )
    {
        return _nNbRequalifications;
    }

    /**
     *
     * @param idSignalement
     */
    public void setIdSignalement( long idSignalement )
    {
        _nIdSignalement = idSignalement;
    }

    /**
     *
     * @param numero
     */
    public void setNumero( String numero )
    {
        _strNumero = numero;
    }

    /**
     *
     * @param priorite
     */
    public void setPriorite( String priorite )
    {
        _strPriorite = priorite;
    }

    /**
     *
     * @param typeSignalement
     */
    public void setTypeSignalement( String typeSignalement )
    {
        _strTypeSignalement = typeSignalement;
    }

    /**
     *
     * @param alias
     */
    public void setAlias( String alias )
    {
        _strAlias = alias;
    }

    /**
     *
     * @param direction
     */
    public void setDirection( String direction )
    {
        _strDirection = direction;
    }

    /**
     *
     * @param quartier
     */
    public void setQuartier( String quartier )
    {
        _strQuartier = quartier;
    }

    /**
     *
     * @param adresse
     */
    public void setAdresse( String adresse )
    {
        _strAdresse = adresse;
    }

    /**
     *
     * @param coordX
     */
    public void setCoordX( Double coordX )
    {
        _nCoordX = coordX;
    }

    /**
     *
     * @param coordY
     */
    public void setCoordY( Double coordY )
    {
        _nCoordY = coordY;
    }

    /**
     *
     * @param arrondissement
     */
    public void setArrondissement( String arrondissement )
    {
        _strArrondissement = arrondissement;
    }

    /**
     *
     * @param secteur
     */
    public void setSecteur( String secteur )
    {
        _strSecteur = secteur;
    }

    /**
     *
     * @param dateCeration
     */
    public void setDateCeration( String dateCeration )
    {
        _strDateCeration = dateCeration;
    }

    /**
     *
     * @param heureCeration
     */
    public void setHeureCeration( String heureCeration )
    {
        _strHeureCeration = heureCeration;
    }

    /**
     *
     * @param etat
     */
    public void setEtat( String etat )
    {
        _strEtat = etat;
    }

    /**
     *
     * @param mailUsager
     */
    public void setMailUsager( String mailUsager )
    {
        _strMailUsager = mailUsager;
    }

    /**
     *
     * @param commentaireUsager
     */
    public void setCommentaireUsager( String commentaireUsager )
    {
        _strCommentaireUsager = commentaireUsager;
    }

    /**
     *
     * @param nbPhotos
     */
    public void setNbPhotos( int nbPhotos )
    {
        _nNbPhotos = nbPhotos;
    }

    /**
     *
     * @param raisonRejet
     */
    public void setRaisonRejet( String raisonRejet )
    {
        _strRaisonRejet = raisonRejet;
    }

    /**
     *
     * @param nbSuivis
     */
    public void setNbSuivis( int nbSuivis )
    {
        _nNbSuivis = nbSuivis;
    }

    /**
     *
     * @param nbFelicitations
     */
    public void setNbFelicitations( int nbFelicitations )
    {
        _nNbFelicitations = nbFelicitations;
    }

    /**
     *
     * @param dateCloture
     */
    public void setDateCloture( String dateCloture )
    {
        _strDateCloture = dateCloture;
    }

    /**
     *
     * @param isPhotoServiceFait
     */
    public void setIsPhotoServiceFait( int isPhotoServiceFait )
    {
        _nIsPhotoServiceFait = isPhotoServiceFait;
    }

    /**
     *
     * @param mailDestinataireCourriel
     */
    public void setMailDestinataireCourriel( String mailDestinataireCourriel )
    {
        _strMailDestinataireCourriel = mailDestinataireCourriel;
    }

    /**
     *
     * @param courrielExpediteur
     */
    public void setCourrielExpediteur( String courrielExpediteur )
    {
        _strCourrielExpediteur = courrielExpediteur;
    }

    /**
     *
     * @param dateEnvoiCourriel
     */
    public void setDateEnvoiCourriel( String dateEnvoiCourriel )
    {
        _strDateEnvoiCourriel = dateEnvoiCourriel;
    }

    /**
     *
     * @param idMailServiceFait
     */
    public void setIdMailServiceFait( int idMailServiceFait )
    {
        _nIdMailServiceFait = idMailServiceFait;
    }

    /**
     *
     * @param executeurServiceFait
     */
    public void setExecuteurServiceFait( String executeurServiceFait )
    {
        _strExecuteurServiceFait = executeurServiceFait;
    }

    /**
     *
     * @param dateDerniereAction
     */
    public void setDateDerniereAction( String dateDerniereAction )
    {
        _strDateDerniereAction = dateDerniereAction;
    }

    /**
     *
     * @param datePrevuTraitement
     */
    public void setDatePrevuTraitement( String datePrevuTraitement )
    {
        _strDatePrevuTraitement = datePrevuTraitement;
    }

    /**
     *
     * @param commentaireAgentTerrain
     */
    public void setCommentaireAgentTerrain( String commentaireAgentTerrain )
    {
        _strCommentaireAgentTerrain = commentaireAgentTerrain;
    }

    /**
     *
     * @param executeurRejet
     */
    public void setExecuteurRejet( String executeurRejet )
    {
        _strExecuteurRejet = executeurRejet;
    }

    /**
     *
     * @param executeurMiseEnSurvreillance
     */
    public void setExecuteurMiseEnSurvreillance( String executeurMiseEnSurvreillance )
    {
        _strExecuteurMiseEnSurvreillance = executeurMiseEnSurvreillance;
    }

    /**
     *
     * @return _strAliasMobile
     */
    public String getAliasMobile( )
    {
        return _strAliasMobile;
    }

    /**
     *
     * @param aliasMobile
     */
    public void setAliasMobile( String aliasMobile )
    {
        _strAliasMobile = aliasMobile;
    }

    /**
     *
     * @param nbRequalifications
     */
    public void setNbRequalifications( int nbRequalifications )
    {
        _nNbRequalifications = nbRequalifications;
    }

    public String getColonneVide1( )
    {
        return _strColonneVide1;
    }

    public String getColonneVide2( )
    {
        return _strColonneVide2;
    }

    public String getColonneVide3( )
    {
        return _strColonneVide3;
    }

    public String getColonneVide4( )
    {
        return _strColonneVide4;
    }

    public String getColonneVide5( )
    {
        return _strColonneVide5;
    }

    public void setColonneVide1( String colonneVide1 )
    {
        _strColonneVide1 = colonneVide1;
    }

    public void setColonneVide2( String colonneVide2 )
    {
        _strColonneVide2 = colonneVide2;
    }

    public void setColonneVide3( String colonneVide3 )
    {
        _strColonneVide3 = colonneVide3;
    }

    public void setColonneVide4( String colonneVide4 )
    {
        _strColonneVide4 = colonneVide4;
    }

    public void setColonneVide5( String colonneVide5 )
    {
        _strColonneVide5 = colonneVide5;
    }

    public String getHeureServiceFait() {
        return _strHeureServiceFait;
    }

    public void setHeureServiceFait(String heureServiceFait) {
        _strHeureServiceFait = heureServiceFait;
    }


}
