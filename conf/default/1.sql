

CREATE TABLE student(application_number Int PRIMARY KEY NOT NULL,
 name Char(50),
 gender Char(50));

CREATE TABLE ielts(
    application_number Int PRIMARY KEY NOT NULL,
    date BIGINT,
    overall float,
    listening float,
    reading float,
    writing float,
    speaking float
);

CREATE TABLE toefl (
    application_number Int PRIMARY KEY NOT NULL,
    date BIGINT,
    cbt_essay Int,
    cbt_listening Int,
    cbt_reading Int,
    cbt_writing Int,
    cbt_total Int,
    pbt_writing Int,
    pbt_reading Int,
    pbt_listening Int,
    pbt_total Int,
    ibt_reading Int,
    ibt_listening Int,
    ibt_speaking Int,
    ibt_writing Int,
    ibt_total Int
);

CREATE TABLE work (
    application_number Int NOT NULL,
    name Char(50),
    designation Char(50),
    date_from BIGINT,
    date_to BIGINT,
    duration Char(50)
);

CREATE TABLE study(
    application_number Int NOT NULL,
    name Char(50),
    location Char(50),
    qualification Char(50),
    specialisation Char(50),
    class_of_honor Char(50),
    end_date BIGINT,
    expect_complete_date BIGINT,
    best_score float,
    gpa float,
    rank Char(50),
    subsidy Char(50),
    name_of_college Char(100),
    qualification_type Char(100)
);

CREATE TABLE university(
    name Char(100) PRIMARY Key NOT NULL,
    country Char(100),
    other Char(500),
    scheme Int,
    rank Int
);

CREATE TABLE company(
    name Char(100) PRIMARY KEY NOT NULL,
    rank Int
);

CREATE TABLE specfactor(
                        name Char(100) PRIMARY KEY NOT NULL,
                        factor float
);

CREATE TABLE univmatch(
                        shortname Char(100) PRIMARY KEY NOT NULL,
                        name Char(100)
);