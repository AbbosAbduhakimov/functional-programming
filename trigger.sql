DROP TABLE IF EXISTS employees;

CREATE TABLE employees(
                          id INT GENERATED ALWAYS AS IDENTITY,
                          first_name VARCHAR(40) NOT NULL,
                          last_name VARCHAR(40) NOT NULL,
                          PRIMARY KEY(id)
);


CREATE TABLE employee_audits (
                                 id INT GENERATED ALWAYS AS IDENTITY,
                                 employee_id INT NOT NULL,
                                 last_name VARCHAR(40) NOT NULL,
                                 changed_on TIMESTAMP(6),
                                 deleted_on TIMESTAMP(6)
);



DROP TABLE employee_audits;
DROP TABLE employees;


CREATE FUNCTION logs_last_name_changes()
RETURNS TRIGGER
LANGUAGE plpgsql
AS
    $$
        BEGIN
            IF new.last_name <> old.last_name THEN
                INSERT INTO employee_audits(employee_id, last_name, changed_on) VALUES (1,'last_name',now());
            end if;
        RETURN new;
            end;
        $$;

DROP FUNCTION logs_last_name_changes();



CREATE FUNCTION logs_last_name_delete()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    if new.last_name = old.last_name then
        INSERT INTO employee_audits(deleted_on) VALUES (1, now());
    end if;
    return new;
end;
$$;




CREATE TRIGGER last_name_changes
    BEFORE UPDATE
    ON employees
    FOR EACH ROW
    EXECUTE PROCEDURE logs_last_name_changes();


CREATE TRIGGER last_name_delete
    AFTER DELETE
    ON employees
    FOR EACH ROW
EXECUTE PROCEDURE logs_last_name_delete();




INSERT INTO employees (first_name, last_name)
VALUES ('John', 'Doe');

INSERT INTO employees (first_name, last_name)
VALUES ('Lily', 'Bush');



SELECT * FROM employees;


UPDATE employees
SET last_name = 'Key'
WHERE id = 2;

ALTER TABLE employees DROP COLUMN last_name;


SELECT * FROM employee_audits;

