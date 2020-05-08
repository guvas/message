create table meldinger
(
   meldingId integer not null,
   melding varchar(255) not null,
   frabruker integer not null,
   tilbruker integer not null,
   primary key(meldingId),
   foreign key(frabruker) references bruker(brukerid),
   foreign key(tilbruker) references bruker(brukerid)
);