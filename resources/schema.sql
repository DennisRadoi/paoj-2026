PRAGMA foreign_keys = ON;

DROP TABLE IF EXISTS comanda_produs;
DROP TABLE IF EXISTS comanda;
DROP TABLE IF EXISTS restaurant_nota;
DROP TABLE IF EXISTS card_bancar;
DROP TABLE IF EXISTS mancare;
DROP TABLE IF EXISTS desert;
DROP TABLE IF EXISTS bautura;
DROP TABLE IF EXISTS produs;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS livrator;
DROP TABLE IF EXISTS utilizator;
DROP TABLE IF EXISTS adresa;

CREATE TABLE adresa (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    judet       TEXT    NOT NULL,
    localitate  TEXT    NOT NULL,
    strada      TEXT    NOT NULL,
    numar       INTEGER NOT NULL,
    bloc        TEXT,
    scara       INTEGER NOT NULL DEFAULT 0,
    apartament  INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE utilizator (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    data_nasterii   TEXT NOT NULL,
    telefon         TEXT NOT NULL,
    email           TEXT NOT NULL,
    varsta          INTEGER NOT NULL,
    nume            TEXT    NOT NULL
);

CREATE TABLE client (
    id          INTEGER PRIMARY KEY,
    adresa_id   INTEGER NOT NULL,
    FOREIGN KEY (id) REFERENCES utilizator (id) ON DELETE CASCADE,
    FOREIGN KEY (adresa_id) REFERENCES adresa (id)
);

CREATE TABLE livrator (
    id               INTEGER PRIMARY KEY,
    vehicul          TEXT,
    este_disponibil  INTEGER NOT NULL DEFAULT 1,
    FOREIGN KEY (id) REFERENCES utilizator (id) ON DELETE CASCADE
);

CREATE TABLE restaurant (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    nume        TEXT    NOT NULL,
    adresa_id   INTEGER NOT NULL,
    FOREIGN KEY (adresa_id) REFERENCES adresa (id)
);

CREATE TABLE restaurant_nota (
    restaurant_id   INTEGER NOT NULL,
    utilizator_id   INTEGER NOT NULL,
    nota            INTEGER NOT NULL CHECK (nota BETWEEN 1 AND 10),
    PRIMARY KEY (restaurant_id, utilizator_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    FOREIGN KEY (utilizator_id) REFERENCES utilizator (id) ON DELETE CASCADE
);


CREATE TABLE produs (
    id              INTEGER    PRIMARY KEY AUTOINCREMENT,
    nume            TEXT    NOT NULL,
    descriere       TEXT,
    pret            REAL    NOT NULL,
    restaurant_id   INTEGER NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE mancare (
    id              INTEGER    PRIMARY KEY AUTOINCREMENT,
    gramaj          INTEGER NOT NULL,
    este_vegan      INTEGER NOT NULL DEFAULT 0,
    este_picant     INTEGER NOT NULL DEFAULT 0,
    nivel_picant    INTEGER NOT NULL DEFAULT 0,
    calorii         INTEGER NOT NULL,
    FOREIGN KEY (id) REFERENCES produs (id) ON DELETE CASCADE
);

CREATE TABLE desert (
    id               INTEGER    PRIMARY KEY AUTOINCREMENT,
    gramaj           INTEGER NOT NULL,
    contine_zahar    INTEGER NOT NULL DEFAULT 0,
    contine_gluten   INTEGER NOT NULL DEFAULT 0,
    contine_lactoza  INTEGER NOT NULL DEFAULT 0,
    este_rece        INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (id) REFERENCES produs (id) ON DELETE CASCADE
);

CREATE TABLE bautura (
    id                   INTEGER    PRIMARY KEY AUTOINCREMENT,
    volum_ml             INTEGER NOT NULL,
    este_carbogazoasa    INTEGER NOT NULL DEFAULT 0,
    contine_alcool       INTEGER NOT NULL DEFAULT 0,
    procent_alcool       REAL    NOT NULL DEFAULT 0,
    rece                 INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (id) REFERENCES produs (id) ON DELETE CASCADE
);

CREATE TABLE card_bancar (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    client_id       INTEGER NOT NULL,
    nume_titular    TEXT    NOT NULL,
    cvv             TEXT    NOT NULL,
    numar           TEXT    NOT NULL,
    luna_exp        INTEGER NOT NULL,
    an_exp          INTEGER NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE
);

CREATE TABLE comanda (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    client_id       INTEGER NOT NULL,
    restaurant_id   INTEGER NOT NULL,
    livrator_id     INTEGER,
    pret_total      REAL NOT NULL,
    status          TEXT    NOT NULL CHECK (
        status IN (
            'INITIALIZATA',
            'IN_PREPARARE',
            'IN_LIVRARE',
            'LIVRATA',
            'RETURNATA',
            'ANULATA'
        )
    ),
    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    FOREIGN KEY (livrator_id) REFERENCES livrator (id) ON DELETE CASCADE
);

CREATE TABLE comanda_produs (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    comanda_id      INTEGER NOT NULL,
    produs_id       INTEGER    NOT NULL,
    FOREIGN KEY (comanda_id) REFERENCES comanda (id) ON DELETE CASCADE,
    FOREIGN KEY (produs_id) REFERENCES produs (id)
);
