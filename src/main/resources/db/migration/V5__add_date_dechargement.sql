-- Migration pour ajouter la colonne date_dechargement dans la table dechargement

ALTER TABLE dechargement 
ADD COLUMN date_dechargement TIMESTAMP NULL;

-- Mettre à jour les enregistrements existants avec la date du chargement associé
UPDATE dechargement d
SET date_dechargement = (
    SELECT c.date_chargement 
    FROM chargement c 
    WHERE c.id = d.chargement_id
)
WHERE date_dechargement IS NULL;

-- Ajouter un commentaire sur la colonne
COMMENT ON COLUMN dechargement.date_dechargement IS 'Date et heure du déchargement (fuseau horaire Tunisie UTC+1)';
