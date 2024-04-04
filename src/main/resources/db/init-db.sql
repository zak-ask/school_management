

CREATE TABLE IF NOT EXISTS filieres(
                                       id int not null AUTO_INCREMENT PRIMARY KEY,
                                       libelle varchar(100) NOT NULL,
                                       description varchar(255) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS semestres(
                                        id int not null AUTO_INCREMENT PRIMARY KEY,
                                        libelle varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS modules(
                                      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
                                      libelle varchar(100) NOT NULL,
                                      description varchar(255) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS programmes(
                                         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
                                         date_debut DATE NOT NULL,
                                         date_fin DATE NOT NULL,
                                         filiere_id INT,
                                         module_id INT,
                                         semestre_id INT,
                                         FOREIGN KEY (module_id) REFERENCES modules(id),
                                         FOREIGN KEY (filiere_id) REFERENCES filieres(id),
                                         FOREIGN KEY (semestre_id) REFERENCES semestres(id)
);

CREATE TABLE IF NOT EXISTS utilisateurs(
                                           id int not null AUTO_INCREMENT PRIMARY KEY UNIQUE,
                                           nom varchar(80) CHARACTER SET UTF8,
                                           prenom varchar(80) CHARACTER SET UTF8,
                                           email varchar(80) CHARACTER SET UTF8,
                                           password varchar(255)
);

CREATE TABLE IF NOT EXISTS admins(
                                     id int not null AUTO_INCREMENT PRIMARY KEY UNIQUE,
                                     utilisateur_id int not null UNIQUE,
                                     FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id)
);

CREATE TABLE IF NOT EXISTS etudiants(
                                        id int not null AUTO_INCREMENT PRIMARY KEY UNIQUE,
                                        cin varchar(30) CHARACTER SET UTF8,
                                        filiere_id INT DEFAULT NULL,
                                        utilisateur_id int not null UNIQUE,
                                        FOREIGN KEY (filiere_id) REFERENCES filieres(id),
                                            FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id)
);

CREATE TABLE IF NOT EXISTS notes(
                                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
                                    libelle varchar(100) NOT NULL,
                                    description varchar(255) DEFAULT NULL,
                                    programme_id INT NOT NULL,
                                    etudiant_id INT NOT NULL,
                                    FOREIGN KEY (programme_id) REFERENCES programmes(id),
                                    FOREIGN KEY (etudiant_id) REFERENCES etudiants(id)
);