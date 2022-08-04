CREATE TABLE "finances" (
  "id" serial NOT NULL,
  PRIMARY KEY ("id"),
  "description" text NOT NULL,
  "value" money NOT NULL,
  "date" date NOT NULL
);