

INSERT INTO bolnica(
	id, adresa, budzet, naziv)
	VALUES (nextval('BOLNICA_SEQ'),'Juznobanatskog Odreda 120', 123, 'Sveti Jovan');
	
INSERT INTO bolnica(
	id, adresa, budzet, naziv)
	VALUES (nextval('BOLNICA_SEQ'),'Mileta Kitica 532', 832, 'Opsta Bolnica Vuk');
	
INSERT INTO bolnica(
	id, adresa, budzet, naziv)
	VALUES (nextval('BOLNICA_SEQ'),'Test 532', 832, 'Opsta Bolnica Mirijad');
	
INSERT INTO bolnica(
	id, adresa, budzet, naziv)
	VALUES (nextval('BOLNICA_SEQ'),'Test 112322', 832, 'Opsta Bolnica Novi Sad');
	
INSERT INTO dijagnoza(
	id, naziv, opis, oznaka)
	VALUES (nextval('DIJAGNOZA_SEQ'), 'Alergija', 'Alergija na jabuke', 'AH1B');
	
INSERT INTO dijagnoza(
	id, naziv, opis, oznaka)
	VALUES (nextval('DIJAGNOZA_SEQ'),'Glavobolja', 'Intenzivna glavobolja', 'CCG2');
	
INSERT INTO dijagnoza(
	id, naziv, opis, oznaka)
	VALUES (nextval('DIJAGNOZA_SEQ'), 'Kijavica', 'Klasicna kijavica', 'KIJ2');
	
INSERT INTO dijagnoza(
	id, naziv, opis, oznaka)
	VALUES (nextval('DIJAGNOZA_SEQ'),'Polomljena Ruka', 'Hibiskus', 'PRH5');
	
INSERT INTO odeljenje(
	id, lokacija, naziv, bolnica)
	VALUES (nextval('ODELJENJE_SEQ'), 'Juznobanatskog Odreda 120', 'Pedijatrija', 1);
	
INSERT INTO odeljenje(
	id, lokacija, naziv, bolnica)
	VALUES (nextval('ODELJENJE_SEQ'), 'Mileta Kitica 532', 'Fiziotarapeut', 2);
	
INSERT INTO odeljenje(
	id, lokacija, naziv, bolnica)
	VALUES (nextval('ODELJENJE_SEQ'), 'Kileraba 123', 'Farmaceut', 4);
	
INSERT INTO pacijent (id, ime, prezime, zdr_osiguranje, datum_rodjenja, dijagnoza, odeljenje)
	VALUES (nextval('PACIJENT_SEQ'), 'Vuk', 'Stojic', True, '19.09.2020', 1, 1);

	
INSERT INTO pacijent (id, ime, prezime, zdr_osiguranje, datum_rodjenja, dijagnoza, odeljenje)
	VALUES (nextval('PACIJENT_SEQ'), 'Pera', 'Peric', False, '24.03.2021', 2, 2);