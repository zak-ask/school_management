

CREATE TABLE IF NOT EXISTS filieres(
                                       id int not null AUTO_INCREMENT PRIMARY KEY,
                                       libelle varchar(100) NOT NULL,
                                       description varchar(255) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS modules(
                                      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                      libelle varchar(100) NOT NULL,
                                      description varchar(255) DEFAULT NULL,
                                      semestre varchar(10) DEFAULT NULL,
                                      filiere_id int not null ,
                                      FOREIGN KEY (filiere_id) REFERENCES filieres(id)
);


CREATE TABLE IF NOT EXISTS utilisateurs(
                                           id int not null AUTO_INCREMENT PRIMARY KEY,
                                           nom varchar(80) CHARACTER SET UTF8,
                                           prenom varchar(80) CHARACTER SET UTF8,
                                           email varchar(80) CHARACTER SET UTF8,
                                           password varchar(255)
);

CREATE TABLE IF NOT EXISTS admins(
                                     id int not null AUTO_INCREMENT PRIMARY KEY,
                                     utilisateur_id int not null ,
                                     FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id)
);

CREATE TABLE IF NOT EXISTS etudiants(
                                        id int not null AUTO_INCREMENT PRIMARY KEY,
                                        cin varchar(30) CHARACTER SET UTF8,
                                        filiere_id INT DEFAULT NULL,
                                        utilisateur_id int not null UNIQUE,
                                        FOREIGN KEY (filiere_id) REFERENCES filieres(id),
                                        FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id)
);

CREATE TABLE IF NOT EXISTS notes(
                                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                    note DOUBLE DEFAULT NULL,
                                    module_id INT NOT NULL,
                                    etudiant_id INT NOT NULL,
                                    FOREIGN KEY (etudiant_id) REFERENCES etudiants(id),
                                    FOREIGN KEY (module_id) REFERENCES modules(id)
);