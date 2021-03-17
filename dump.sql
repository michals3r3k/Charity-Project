--
-- PostgreSQL database dump
--

-- Dumped from database version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: charity-donation; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA "charity-donation";


ALTER SCHEMA "charity-donation" OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: app_user; Type: TABLE; Schema: charity-donation; Owner: postgres
--

CREATE TABLE "charity-donation".app_user (
    id bigint NOT NULL,
    email character varying(255),
    enabled boolean NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255)
);


ALTER TABLE "charity-donation".app_user OWNER TO postgres;

--
-- Name: app_user_id_seq; Type: SEQUENCE; Schema: charity-donation; Owner: postgres
--

CREATE SEQUENCE "charity-donation".app_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "charity-donation".app_user_id_seq OWNER TO postgres;

--
-- Name: app_user_id_seq; Type: SEQUENCE OWNED BY; Schema: charity-donation; Owner: postgres
--

ALTER SEQUENCE "charity-donation".app_user_id_seq OWNED BY "charity-donation".app_user.id;


--
-- Name: category; Type: TABLE; Schema: charity-donation; Owner: postgres
--

CREATE TABLE "charity-donation".category (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE "charity-donation".category OWNER TO postgres;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: charity-donation; Owner: postgres
--

CREATE SEQUENCE "charity-donation".category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "charity-donation".category_id_seq OWNER TO postgres;

--
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: charity-donation; Owner: postgres
--

ALTER SEQUENCE "charity-donation".category_id_seq OWNED BY "charity-donation".category.id;


--
-- Name: confirmation_token; Type: TABLE; Schema: charity-donation; Owner: postgres
--

CREATE TABLE "charity-donation".confirmation_token (
    id bigint NOT NULL,
    confirmed_at timestamp without time zone,
    token character varying(255),
    token_type character varying(255),
    used boolean NOT NULL,
    user_id bigint
);


ALTER TABLE "charity-donation".confirmation_token OWNER TO postgres;

--
-- Name: confirmation_token_id_seq; Type: SEQUENCE; Schema: charity-donation; Owner: postgres
--

CREATE SEQUENCE "charity-donation".confirmation_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "charity-donation".confirmation_token_id_seq OWNER TO postgres;

--
-- Name: confirmation_token_id_seq; Type: SEQUENCE OWNED BY; Schema: charity-donation; Owner: postgres
--

ALTER SEQUENCE "charity-donation".confirmation_token_id_seq OWNED BY "charity-donation".confirmation_token.id;


--
-- Name: donation; Type: TABLE; Schema: charity-donation; Owner: postgres
--

CREATE TABLE "charity-donation".donation (
    id bigint NOT NULL,
    city character varying(255),
    phone_number character varying(255),
    pick_up_date date,
    pick_up_time time without time zone,
    pickup_comment character varying(255),
    quantity integer,
    street character varying(255),
    zip_code character varying(255),
    institution_id bigint,
    user_id bigint
);


ALTER TABLE "charity-donation".donation OWNER TO postgres;

--
-- Name: donation_category; Type: TABLE; Schema: charity-donation; Owner: postgres
--

CREATE TABLE "charity-donation".donation_category (
    donation_id bigint NOT NULL,
    categories_id bigint NOT NULL
);


ALTER TABLE "charity-donation".donation_category OWNER TO postgres;

--
-- Name: donation_id_seq; Type: SEQUENCE; Schema: charity-donation; Owner: postgres
--

CREATE SEQUENCE "charity-donation".donation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "charity-donation".donation_id_seq OWNER TO postgres;

--
-- Name: donation_id_seq; Type: SEQUENCE OWNED BY; Schema: charity-donation; Owner: postgres
--

ALTER SEQUENCE "charity-donation".donation_id_seq OWNED BY "charity-donation".donation.id;


--
-- Name: institution; Type: TABLE; Schema: charity-donation; Owner: postgres
--

CREATE TABLE "charity-donation".institution (
    id bigint NOT NULL,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE "charity-donation".institution OWNER TO postgres;

--
-- Name: institution_id_seq; Type: SEQUENCE; Schema: charity-donation; Owner: postgres
--

CREATE SEQUENCE "charity-donation".institution_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "charity-donation".institution_id_seq OWNER TO postgres;

--
-- Name: institution_id_seq; Type: SEQUENCE OWNED BY; Schema: charity-donation; Owner: postgres
--

ALTER SEQUENCE "charity-donation".institution_id_seq OWNED BY "charity-donation".institution.id;


--
-- Name: role; Type: TABLE; Schema: charity-donation; Owner: postgres
--

CREATE TABLE "charity-donation".role (
    id bigint NOT NULL,
    role_type character varying(255)
);


ALTER TABLE "charity-donation".role OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: charity-donation; Owner: postgres
--

CREATE SEQUENCE "charity-donation".role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "charity-donation".role_id_seq OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: charity-donation; Owner: postgres
--

ALTER SEQUENCE "charity-donation".role_id_seq OWNED BY "charity-donation".role.id;


--
-- Name: user_role; Type: TABLE; Schema: charity-donation; Owner: postgres
--

CREATE TABLE "charity-donation".user_role (
    user_id bigint NOT NULL,
    roles_id bigint NOT NULL
);


ALTER TABLE "charity-donation".user_role OWNER TO postgres;

--
-- Name: app_user id; Type: DEFAULT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".app_user ALTER COLUMN id SET DEFAULT nextval('"charity-donation".app_user_id_seq'::regclass);


--
-- Name: category id; Type: DEFAULT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".category ALTER COLUMN id SET DEFAULT nextval('"charity-donation".category_id_seq'::regclass);


--
-- Name: confirmation_token id; Type: DEFAULT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".confirmation_token ALTER COLUMN id SET DEFAULT nextval('"charity-donation".confirmation_token_id_seq'::regclass);


--
-- Name: donation id; Type: DEFAULT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".donation ALTER COLUMN id SET DEFAULT nextval('"charity-donation".donation_id_seq'::regclass);


--
-- Name: institution id; Type: DEFAULT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".institution ALTER COLUMN id SET DEFAULT nextval('"charity-donation".institution_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".role ALTER COLUMN id SET DEFAULT nextval('"charity-donation".role_id_seq'::regclass);


--
-- Data for Name: app_user; Type: TABLE DATA; Schema: charity-donation; Owner: postgres
--

COPY "charity-donation".app_user (id, email, enabled, first_name, last_name, password) FROM stdin;
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: charity-donation; Owner: postgres
--

COPY "charity-donation".category (id, name) FROM stdin;
1	ubrania, które nadają się do ponownego użycia
2	ubrania, do wyrzucenia
3	zabawki
4	książki
5	inne
\.


--
-- Data for Name: confirmation_token; Type: TABLE DATA; Schema: charity-donation; Owner: postgres
--

COPY "charity-donation".confirmation_token (id, confirmed_at, token, token_type, used, user_id) FROM stdin;
\.


--
-- Data for Name: donation; Type: TABLE DATA; Schema: charity-donation; Owner: postgres
--

COPY "charity-donation".donation (id, city, phone_number, pick_up_date, pick_up_time, pickup_comment, quantity, street, zip_code, institution_id, user_id) FROM stdin;
\.


--
-- Data for Name: donation_category; Type: TABLE DATA; Schema: charity-donation; Owner: postgres
--

COPY "charity-donation".donation_category (donation_id, categories_id) FROM stdin;
\.


--
-- Data for Name: institution; Type: TABLE DATA; Schema: charity-donation; Owner: postgres
--

COPY "charity-donation".institution (id, description, name) FROM stdin;
1	Pomoc dzieciom z ubogich rodzin	Dbam o Zdrowie
2	Pomoc wybudzaniu dzieci ze śpiączki	A kogo
3	Pomoc dla osób nie posiadających miejsca zamieszkania	Bez domu
4	Pomoc osobom znajdującym się w trudnej sytuacji życiowej	Dla dzieci
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: charity-donation; Owner: postgres
--

COPY "charity-donation".role (id, role_type) FROM stdin;
1	ROLE_USER
2	ROLE_ADMIN
\.


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: charity-donation; Owner: postgres
--

COPY "charity-donation".user_role (user_id, roles_id) FROM stdin;
\.


--
-- Name: app_user_id_seq; Type: SEQUENCE SET; Schema: charity-donation; Owner: postgres
--

SELECT pg_catalog.setval('"charity-donation".app_user_id_seq', 1, true);


--
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: charity-donation; Owner: postgres
--

SELECT pg_catalog.setval('"charity-donation".category_id_seq', 1, false);


--
-- Name: confirmation_token_id_seq; Type: SEQUENCE SET; Schema: charity-donation; Owner: postgres
--

SELECT pg_catalog.setval('"charity-donation".confirmation_token_id_seq', 1, true);


--
-- Name: donation_id_seq; Type: SEQUENCE SET; Schema: charity-donation; Owner: postgres
--

SELECT pg_catalog.setval('"charity-donation".donation_id_seq', 1, false);


--
-- Name: institution_id_seq; Type: SEQUENCE SET; Schema: charity-donation; Owner: postgres
--

SELECT pg_catalog.setval('"charity-donation".institution_id_seq', 1, false);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: charity-donation; Owner: postgres
--

SELECT pg_catalog.setval('"charity-donation".role_id_seq', 1, false);


--
-- Name: app_user app_user_pkey; Type: CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: confirmation_token confirmation_token_pkey; Type: CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".confirmation_token
    ADD CONSTRAINT confirmation_token_pkey PRIMARY KEY (id);


--
-- Name: donation donation_pkey; Type: CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".donation
    ADD CONSTRAINT donation_pkey PRIMARY KEY (id);


--
-- Name: institution institution_pkey; Type: CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".institution
    ADD CONSTRAINT institution_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, roles_id);


--
-- Name: donation_category fk1r52gnkelqnhw4tfgoc3oila4; Type: FK CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".donation_category
    ADD CONSTRAINT fk1r52gnkelqnhw4tfgoc3oila4 FOREIGN KEY (donation_id) REFERENCES "charity-donation".donation(id);


--
-- Name: donation fk1r66ajegegamb0csj23kga2d; Type: FK CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".donation
    ADD CONSTRAINT fk1r66ajegegamb0csj23kga2d FOREIGN KEY (user_id) REFERENCES "charity-donation".app_user(id);


--
-- Name: confirmation_token fke0flsojr3yhckm24wh4e4xyj4; Type: FK CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".confirmation_token
    ADD CONSTRAINT fke0flsojr3yhckm24wh4e4xyj4 FOREIGN KEY (user_id) REFERENCES "charity-donation".app_user(id);


--
-- Name: user_role fkeog8p06nu33ihk13roqnrp1y6; Type: FK CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".user_role
    ADD CONSTRAINT fkeog8p06nu33ihk13roqnrp1y6 FOREIGN KEY (roles_id) REFERENCES "charity-donation".role(id);


--
-- Name: donation_category fkg74x2imonfs6uipcjacp1y1rm; Type: FK CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".donation_category
    ADD CONSTRAINT fkg74x2imonfs6uipcjacp1y1rm FOREIGN KEY (categories_id) REFERENCES "charity-donation".category(id);


--
-- Name: user_role fkg7fr1r7o0fkk41nfhnjdyqn7b; Type: FK CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".user_role
    ADD CONSTRAINT fkg7fr1r7o0fkk41nfhnjdyqn7b FOREIGN KEY (user_id) REFERENCES "charity-donation".app_user(id);


--
-- Name: donation fkqiaintp9rxqwmmpwj3y5dcwd1; Type: FK CONSTRAINT; Schema: charity-donation; Owner: postgres
--

ALTER TABLE ONLY "charity-donation".donation
    ADD CONSTRAINT fkqiaintp9rxqwmmpwj3y5dcwd1 FOREIGN KEY (institution_id) REFERENCES "charity-donation".institution(id);


--
-- PostgreSQL database dump complete
--

