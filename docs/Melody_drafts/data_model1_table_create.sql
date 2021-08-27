CREATE TABLE public.customer (
    c_cid integer NOT NULL,
    c_firstname char(26) NOT NULL,
    c_lastname char(26) NOT NULL,
    c_homeaddress char(200) NOT NULL,
    c_phonenumber integer NOT NULL,
    PRIMARY KEY (c_cid)
);


CREATE TABLE public.servicerequest (
    sr_tid integer NOT NULL,
    sr_comments text NOT NULL,
    sr_dateopened timestamp without time zone NOT NULL,
    sr_bill numeric NOT NULL,
    c_id integer NOT NULL,
    v_vin integer NOT NULL,
    PRIMARY KEY (sr_tid)
);

CREATE INDEX ON public.servicerequest
    (c_id);
CREATE INDEX ON public.servicerequest
    (v_vin);


CREATE TABLE public.mechanic (
    m_eid integer NOT NULL,
    m_firstname char(20) NOT NULL,
    m_lastname char(20) NOT NULL,
    m_experience date NOT NULL,
    PRIMARY KEY (m_eid)
);


CREATE TABLE public.cars (
    v_vin integer NOT NULL,
    v_make char(15) NOT NULL,
    v_model char(15) NOT NULL,
    v_year date NOT NULL,
    PRIMARY KEY (v_vin)
);


CREATE TABLE public.invoice (
    i_tid integer NOT NULL,
    i_totalamount numeric NOT NULL,
    i_comments text NOT NULL,
    i_dateclosed timestamp without time zone NOT NULL,
    i_ticketowner char(20) NOT NULL,
    tid integer NOT NULL,
    m_eid integer NOT NULL,
    PRIMARY KEY (i_tid)
);

CREATE INDEX ON public.invoice
    (tid);
CREATE INDEX ON public.invoice
    (m_eid);


CREATE TABLE public.owner (
    o_oid integer NOT NULL,
    o_cid integer NOT NULL,
    o_vin integer NOT NULL,
    v_vin integer NOT NULL,
    c_id integer NOT NULL,
    PRIMARY KEY (o_oid)
);

CREATE INDEX ON public.owner
    (v_vin);
CREATE INDEX ON public.owner
    (c_id);


ALTER TABLE public.servicerequest ADD CONSTRAINT FK_servicerequest__c_id FOREIGN KEY (c_id) REFERENCES public.customer(c_cid);
ALTER TABLE public.servicerequest ADD CONSTRAINT FK_servicerequest__v_vin FOREIGN KEY (v_vin) REFERENCES public.cars(v_vin);
ALTER TABLE public.invoice ADD CONSTRAINT FK_invoice__tid FOREIGN KEY (tid) REFERENCES public.servicerequest(sr_tid);
ALTER TABLE public.invoice ADD CONSTRAINT FK_invoice__m_eid FOREIGN KEY (m_eid) REFERENCES public.mechanic(m_eid);
ALTER TABLE public.owner ADD CONSTRAINT FK_owner__v_vin FOREIGN KEY (v_vin) REFERENCES public.cars(v_vin);
ALTER TABLE public.owner ADD CONSTRAINT FK_owner__c_id FOREIGN KEY (c_id) REFERENCES public.customer(c_cid);