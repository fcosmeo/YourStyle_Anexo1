create database bd_yourstyle
go

use bd_yourstyle
go

Create Table tables(	name varchar(150) not null primary key,
						id int not null default '0',
						registration_date datetime not null default getdate(),
						modification_date datetime null
					)
Go

Insert Into tables(name,id) 
Values('people',0)
Go

create Table people(	id int not null primary key,
						first_name varchar(150) not null,
						last_name varchar(150) not null,
						dni varchar(20) not null,
						birth_date date not null,
						email varchar(150) not null,
						cell_phone varchar(15) null,
						status_id int not null default 1,
						registration_date datetime not null default getdate(),
						modification_date datetime null,
						send_mail tinyint null
				    )
Go

Create Table status (	id int not null primary key,
						description varchar(30) not null,
						registration_date datetime not null default getdate(),
					) 
Go

Create Procedure sp_getIdTable(	@table_name varchar(150),
								@table_id int output
)
As
Begin
    Declare @row_count int=0

    SELECT	@row_count=count(*),
			@table_id=max(id)    
    FROM tables with(nolock)
    WHERE name=@table_name

    if isnull(@row_count,0)=0
    begin
      set @table_id=1

      Insert Into tables(name,id,registration_date) Values(@table_name,@table_id,getdate());
	end
    else
    begin
      set @table_id=@table_id+1

      Update tables
		Set	id=@table_id,
			modification_date=getdate()
      where name=@table_name;
    end
End
go

alter table people add constraint r1 foreign key (status_id) references Status(id)
go

Insert Into status(id,description)
values('0','Inactivo')
Go
Insert Into status(id,description)
values('1','Activo')
Go




