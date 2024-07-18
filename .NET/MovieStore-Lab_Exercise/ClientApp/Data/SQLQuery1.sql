
CREATE TABLE Clients
(
    id integer  PRIMARY KEY,
    "name" VARCHAR(250),
	totalCost float
  );

INSERT INTO Clients (id, "name", totalCost) VALUES (1, 'John Doe', 12.99); 
INSERT INTO Clients (id,  "name", totalCost) VALUES (2,'Jane Smith', 29.99);