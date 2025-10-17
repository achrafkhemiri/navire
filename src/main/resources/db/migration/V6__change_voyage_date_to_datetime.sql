-- Migration pour changer le type de la colonne date de DATE à TIMESTAMP
-- dans la table voyages pour inclure l'heure et les minutes

ALTER TABLE voyages 
MODIFY COLUMN date TIMESTAMP NOT NULL;
