CREATE TYPE issue_statuses AS ENUM
('NEW','IN_PROGRESS','DONE','REJECTED');

CREATE TABLE "pets" (
	"id" bigserial NOT NULL,
	"name" varchar(100) NOT NULL,
	"description" varchar(400) NOT NULL,
	"reg_date" DATE NOT NULL,
	"id_owner" bigint NOT NULL,
	CONSTRAINT pets_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "owners" (
	"id" bigserial NOT NULL,
	"first_name" varchar(100) NOT NULL,
	"last_name" varchar(100) NOT NULL,
	"middle_name" varchar(100),
  "address" TEXT NOT NULL,
	"id_user" bigint NOT NULL UNIQUE,
	CONSTRAINT owners_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_roles" (
	"id" serial NOT NULL,
	"name" varchar(20) NOT NULL UNIQUE,
	CONSTRAINT user_roles_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "emloyees" (
	"id" bigserial NOT NULL,
	"first_name" varchar(100) NOT NULL,
	"last_name" varchar(100) NOT NULL,
	"middle_name" varchar(100),
  "experience" smallint NOT NULL,
	"id_user" bigint NOT NULL UNIQUE,
	CONSTRAINT emloyees_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "issues" (
	"id" bigserial NOT NULL,
	"id_emloyee" bigint NOT NULL,
	"id_pet" bigint NOT NULL,
	"description" TEXT NOT NULL,
	"created_date" DATE NOT NULL,
	"status" issue_statuses NOT NULL,
	CONSTRAINT issues_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "users" (
	"id" bigserial NOT NULL,
	"login" varchar(20) NOT NULL UNIQUE,
	"password" TEXT NOT NULL,
	"id_role" smallint NOT NULL,
	"created_date" DATE NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "pets" ADD CONSTRAINT "pets_fk0" FOREIGN KEY ("id_owner") REFERENCES "owners"("id");

ALTER TABLE "owners" ADD CONSTRAINT "owners_fk0" FOREIGN KEY ("id_user") REFERENCES "users"("id");


ALTER TABLE "emloyees" ADD CONSTRAINT "emloyees_fk0" FOREIGN KEY ("id_user") REFERENCES "users"("id");

ALTER TABLE "issues" ADD CONSTRAINT "issues_fk0" FOREIGN KEY ("id_emloyee") REFERENCES "emloyees"("id");
ALTER TABLE "issues" ADD CONSTRAINT "issues_fk1" FOREIGN KEY ("id_pet") REFERENCES "pets"("id");

ALTER TABLE "users" ADD CONSTRAINT "users_fk0" FOREIGN KEY ("id_role") REFERENCES "user_roles"("id");

