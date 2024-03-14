-- a ignorer ce fichier, c'est temporairement utiliser pour la sauvegarde sur github

select
	ss.id_signalement,
	se.numero,
	priorite,
	type_signalement,
	alias,
	alias_mobile,
	direction,
	quartier,
	adresse,
	coord_x,
	coord_y,
	arrondissement,
	secteur,
	se.date_creation,
	heure_creation,
	etat,
	mail_usager,
	commentaire_usager,
	nb_photos,
	raisons_rejet,
	nb_suivis,
	nb_felicitations,
	se.date_cloture,
	is_photo_service_fait,
	mail_destinataire_courriel,
	se.courriel_expediteur,
	date_envoi_courriel,
	id_mail_service_fait,
	executeur_service_fait,
	se.date_derniere_action,
	date_prevu_traitement,
	se.commentaire_agent_terrain,
	executeur_rejet,
	executeur_mise_surveillance,
	nb_requalifications,
	to_char(ss.service_fait_date_passage, 'HH24:MI') heure_sf,
	executeur_requalification,
	executeur_requalification_bis,
	premier_id_type_signalement,
	premier_direction,
	se.precision_terrain,
	TO_CHAR(ss.date_creation, 'dd/mm/yyyy ; HH24:MI:SS') as ts_creation,
	TO_CHAR(dateHistory.date_cloture, 'dd/mm/yyyy ; HH24:MI:SS') as ts_date_cloture,
	TO_CHAR(lastAction.date_derniere_action, 'dd/mm/yyyy ; HH24:MI:SS') as ts_date_derniere_action,

	--changed
	TO_CHAR(lastActionProgrammer.date_prevue_traitement, 'dd/mm/yyyy ; HH24:MI:SS') as ts_prevue_traitement,

	TO_CHAR(ss.courriel_date, 'dd/mm/yyyy ; HH24:MI:SS') as ts_courriel_date,
	TO_CHAR(ss.service_fait_date_passage, 'dd/mm/yyyy ; HH24:MI:SS') as ts_service_fait_date_passage

from
	signalement_export se
join signalement_signalement ss on
	ss.id_signalement = se.id_signalement
left join (
	select id_resource , max(creation_date) as date_derniere_action
	from workflow_resource_history
	group by id_resource) as lastAction on
	ss.id_signalement = lastAction.id_resource

-- changed: date cloture - last date of action rejeté or service fait
left join (
	select
		 wrh.id_resource,
		 max(wrh.creation_date) as date_cloture
	from signalement_signalement ss
	 left join workflow_resource_history wrh on
			ss.id_signalement = wrh.id_resource
	 left join workflow_resource_workflow wrw on
			ss.id_signalement = wrw.id_resource
	left join workflow_state ws on
		wrw.id_state = ws.id_state
	where ws.name in ('Rejeté', 'Service fait')
	group by wrh.id_resource
 ) as dateHistory on
ss.id_signalement = dateHistory.id_resource
-- changed: date prevue traitement based on programmer, reprogrammer
left join
 (select id_resource , max(creation_date) as date_prevue_traitement
from workflow_resource_history wrh
left join workflow_action wa
on wrh.id_action = wa.id_action
where wa.name like 'Programmer%' or  wa.name like 'Reprogrammer%'
group by id_resource) as lastActionProgrammer
on ss.id_signalement = lastActionProgrammer.id_resource

