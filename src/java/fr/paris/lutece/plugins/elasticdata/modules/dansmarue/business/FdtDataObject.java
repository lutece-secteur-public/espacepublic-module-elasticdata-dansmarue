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

import java.util.List;

import fr.paris.lutece.plugins.elasticdata.business.AbstractDataObject;

/**
 * FdtDataObject
 */
public class FdtDataObject extends AbstractDataObject
{

    // Variables declarations
    private long          _nIdFdt;
    private String        _strNom;
    private String        _strCreateur;
    private String        _strDateCreation;
    private String        _strDateModification;
    private String        _strDirection;
    private String        _strEntite;
    private List<String>  _listSignalementNumeros;
    private List<Integer> _listSignalementIds;
    private int           _nNbCosultation;

    public long getIdFdt( )
    {
        return _nIdFdt;
    }

    public String getNom( )
    {
        return _strNom;
    }

    public String getCreateur( )
    {
        return _strCreateur;
    }

    public String getDateCreation( )
    {
        return _strDateCreation;
    }

    public String getDateModification( )
    {
        return _strDateModification;
    }

    public String getDirection( )
    {
        return _strDirection;
    }

    public String getEntite( )
    {
        return _strEntite;
    }

    public List<String> getListSignalementNumeros( )
    {
        return _listSignalementNumeros;
    }

    public List<Integer> getListSignalementIds( )
    {
        return _listSignalementIds;
    }

    public int getNbCosultation( )
    {
        return _nNbCosultation;
    }

    public void setIdFdt( long idFdt )
    {
        _nIdFdt = idFdt;
    }

    public void setNom( String nom )
    {
        _strNom = nom;
    }

    public void setCreateur( String createur )
    {
        _strCreateur = createur;
    }

    public void setDateCreation( String dateCreation )
    {
        _strDateCreation = dateCreation;
    }

    public void setDateModification( String dateModification )
    {
        _strDateModification = dateModification;
    }

    public void setDirection( String direction )
    {
        _strDirection = direction;
    }

    public void setEntite( String entite )
    {
        _strEntite = entite;
    }

    public void setListSignalementNumeros( List<String> listSignalementNumeros )
    {
        _listSignalementNumeros = listSignalementNumeros;
    }

    public void setListSignalementIds( List<Integer> listSignalementIds )
    {
        _listSignalementIds = listSignalementIds;
    }

    public void setNbCosultation( int nbCosultation )
    {
        _nNbCosultation = nbCosultation;
    }

}
