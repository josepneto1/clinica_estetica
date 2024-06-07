--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.1

-- Started on 2024-06-05 09:02:15 -03

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
-- TOC entry 3405 (class 1262 OID 16447)
-- Name: clinica_estetica; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE clinica_estetica WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE clinica_estetica OWNER TO postgres;

\connect clinica_estetica

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 224 (class 1259 OID 16911)
-- Name: acesso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.acesso (
                               id bigint NOT NULL,
                               descricao character varying(255) NOT NULL
);


ALTER TABLE public.acesso OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16976)
-- Name: atendimento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.atendimento (
                                    id bigint NOT NULL,
                                    data_atendimento date NOT NULL,
                                    status character varying(255) NOT NULL,
                                    valor_desconto numeric(38,2),
                                    valor_total numeric(38,2) NOT NULL,
                                    cliente_id bigint NOT NULL,
                                    funcionario_id bigint NOT NULL,
                                    procedimento_id bigint NOT NULL,
                                    CONSTRAINT atendimento_status_check CHECK (((status)::text = ANY ((ARRAY['ABERTA'::character varying, 'QUITADA'::character varying])::text[])))
);


ALTER TABLE public.atendimento OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16982)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
                                id bigint NOT NULL,
                                email character varying(255),
                                nome character varying(50) NOT NULL,
                                telefone character varying(15) NOT NULL,
                                cidade character varying(30),
                                cpf character varying(11) NOT NULL,
                                data_nascimento date
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16987)
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcionario (
                                    id bigint NOT NULL,
                                    email character varying(255),
                                    nome character varying(50) NOT NULL,
                                    telefone character varying(15) NOT NULL,
                                    cpf character varying(11) NOT NULL,
                                    data_nascimento date,
                                    descricao character varying(20) NOT NULL
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 16992)
-- Name: procedimento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.procedimento (
                                     id bigint NOT NULL,
                                     nome character varying(30) NOT NULL,
                                     preco numeric(38,2) NOT NULL
);


ALTER TABLE public.procedimento OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16937)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
                                id bigint NOT NULL,
                                login character varying(255) NOT NULL,
                                senha character varying(255) NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16944)
-- Name: usuarios_acesso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios_acesso (
                                        usuario_id bigint NOT NULL,
                                        acesso_id bigint NOT NULL
);


ALTER TABLE public.usuarios_acesso OWNER TO postgres;

--
-- TOC entry 3393 (class 0 OID 16911)
-- Dependencies: 224
-- Data for Name: acesso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3396 (class 0 OID 16976)
-- Dependencies: 227
-- Data for Name: atendimento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3397 (class 0 OID 16982)
-- Dependencies: 228
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3398 (class 0 OID 16987)
-- Dependencies: 229
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3399 (class 0 OID 16992)
-- Dependencies: 230
-- Data for Name: procedimento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3394 (class 0 OID 16937)
-- Dependencies: 225
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3395 (class 0 OID 16944)
-- Dependencies: 226
-- Data for Name: usuarios_acesso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3230 (class 2606 OID 16915)
-- Name: acesso acesso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acesso
    ADD CONSTRAINT acesso_pkey PRIMARY KEY (id);


--
-- TOC entry 3238 (class 2606 OID 16981)
-- Name: atendimento atendimento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.atendimento
    ADD CONSTRAINT atendimento_pkey PRIMARY KEY (id);


--
-- TOC entry 3240 (class 2606 OID 16986)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- TOC entry 3242 (class 2606 OID 16991)
-- Name: funcionario funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (id);


--
-- TOC entry 3244 (class 2606 OID 16996)
-- Name: procedimento procedimento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.procedimento
    ADD CONSTRAINT procedimento_pkey PRIMARY KEY (id);


--
-- TOC entry 3234 (class 2606 OID 16950)
-- Name: usuarios_acesso uk8bak9jswon2id2jbunuqlfl9e; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT uk8bak9jswon2id2jbunuqlfl9e UNIQUE (acesso_id);


--
-- TOC entry 3232 (class 2606 OID 16943)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 3236 (class 2606 OID 16948)
-- Name: usuarios_acesso usuario_unico_acesso; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT usuario_unico_acesso UNIQUE (usuario_id, acesso_id);


--
-- TOC entry 3245 (class 2606 OID 16966)
-- Name: usuarios_acesso acesso_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT acesso_fk FOREIGN KEY (acesso_id) REFERENCES public.acesso(id);


--
-- TOC entry 3247 (class 2606 OID 16997)
-- Name: atendimento cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.atendimento
    ADD CONSTRAINT cliente_fk FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);


--
-- TOC entry 3248 (class 2606 OID 17002)
-- Name: atendimento funcionario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.atendimento
    ADD CONSTRAINT funcionario_fk FOREIGN KEY (funcionario_id) REFERENCES public.funcionario(id);


--
-- TOC entry 3249 (class 2606 OID 17007)
-- Name: atendimento procedimento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.atendimento
    ADD CONSTRAINT procedimento_fk FOREIGN KEY (procedimento_id) REFERENCES public.procedimento(id);


--
-- TOC entry 3246 (class 2606 OID 16971)
-- Name: usuarios_acesso usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT usuario_fk FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


-- Completed on 2024-06-05 09:02:15 -03

--
-- PostgreSQL database dump complete
--

