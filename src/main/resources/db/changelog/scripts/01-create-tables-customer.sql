create table customer (
    customer_id UUID PRIMARY KEY,
    document_type varchar(2),
    document_number varchar(20),
    first_name varchar(100),
    last_name varchar(100),
    status varchar(1)
);

CREATE UNIQUE INDEX ux_customer_doc ON customer(document_type, document_number);

create table contact_data (
    contact_id UUID PRIMARY KEY,
    customer_id UUID,
    data_type varchar(2),
    data varchar (100),
    status varchar(1),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);





