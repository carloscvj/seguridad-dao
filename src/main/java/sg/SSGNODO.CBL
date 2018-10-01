       IDENTIFICATION DIVISION.

       PROGRAM-ID.    SSGNODO.
       AUTHOR.        El hacedor de SERVIDORES.
       SECURITY.      SERVIDOR DE FSGNODO.

        ENVIRONMENT DIVISION.

       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           COPY "FSGNODO-SEL".

       DATA DIVISION.
       FILE SECTION.
           COPY "FSGNODO-FD".

       WORKING-STORAGE SECTION.

       01 ESCRIBIBLE  PIC X IS EXTERNAL.
       01 USUARIO  PIC X(15) IS EXTERNAL.
       01 ABM      PIC X.
       01 REGISTRO PIC X(16384).
       01 TRAZAR   PIC XXXXX.

       01 JRSGNODO.
         03 JCLA PIC X(64).
         03 JFILLER PIC X(64).
       01  VALORDIR          PIC X(128).
       01  NOMBRDIR          PIC X(6).
       01  ERRORES.
           03 ERROR-1        PIC X.
           03 ERROR-2        PIC X.
           03 ERROR-2N REDEFINES ERROR-2 PIC 9 COMP-X.

       01 NOMBREFSGNODO                PIC X(256).
       01 ABIERTO                     PIC 9.
       01 IGNORELOCK                  PIC 9.
       01 INVALIDKEY                  PIC 9.
       01 INVALIDKEYNEXT              PIC 9.
       01 INVALIDKEYPREV              PIC 9.
       01 CONVERTIR                   PIC 9.
       01 PANII                       PIC 99.
       01 PANJJ                       PIC 99.
       01 II                          PIC 99999.
       01 JJ                          PIC 99999.
       01 KK                          PIC 99999.
       01 LONGI                       PIC 99999.
       01 ESTADO                      PIC XX.
       01 PALPRUPARJ                   PIC X(255).
       01 OPERACION                   PIC X(128).
       01 VALORES                     PIC X(384).
       01 BUFERENT                    PIC X(384).
       01 MIBUF                       PIC X(384).
       01 CMP-1                       PIC X(384).
       01 CMP-2                       PIC X(384).
       01 MILONGI                     PIC 99999.
       01 ENTEXTO PIC X(128).
       01 ELNUMERO USAGE IS COMP-2.
       01 ENPIC PIC -9(14).9999.
       01 COMPIC PIC -9(14).9999.
       01 FINPIC PIC -9(14).9999.
       01 ENVALOR PIC S9(14)V9999 SIGN LEADING SEPARATE.
       01 ENLETRA REDEFINES ENVALOR.
          03 ELENTERO  PIC X(15).
          03 ELDECIMAL PIC X(4).
       01 PRIMERO PIC X(128).
       01 SEGUNDO PIC X(128).
       01 TERCERO PIC X(128).
       01 ELRESTO PIC X(128).
       01 ELRESTO1 PIC X(128).
       01 ELRESTO2 PIC X(128).

       01 SEPARADOR PIC XXX VALUE "|@|".
       01 BUFER-DIR.
          03 BUF-1 PIC X OCCURS 256.
       01 DIRECTORIO.
          03 DIR-1 PIC X OCCURS 256.
       01 X-ERROR PIC X(80).
       01 CUANTOS PIC 99 VALUE 79.
       01 LINKA PIC 9 VALUE 0.

         PROCEDURE DIVISION.

       PROGRAMA SECTION.
       INICIALES.
           IF ABIERTO < 1 OR > 4
              MOVE "mnu/SG/FSGNODO" TO NOMBREFSGNODO
              MOVE 4 TO ABIERTO
              INITIALIZE RSGNODO
              INITIALIZE CONVERTIR
              MOVE 1 TO IGNORELOCK
              INITIALIZE INVALIDKEY
              MOVE "00" TO ERRORES
           END-IF.
       PROCESOS.
           IF ESCRIBIBLE = "P"
              CALL "Leer" USING BUFERENT
           ELSE
              ACCEPT BUFERENT FROM SYSIN
           END-IF.
           PERFORM COGE-TRABAJO.
           EVALUATE OPERACION
              WHEN "TODOS" PERFORM CARGATODOS
              WHEN "TRAZAR"
                 MOVE VALORES TO TRAZAR
              WHEN "SNO"  PERFORM PONNOMBRE
              WHEN "OIN"  PERFORM ABREINPUT
              WHEN "OIO"  PERFORM ABREIO
              WHEN "OOU"  PERFORM ABREOUTPUT
              WHEN "REA"  PERFORM LEER
              WHEN "WRI"  PERFORM ESCRIBIR
              WHEN "REW"  PERFORM REESCRIBIR
              WHEN "DEL"  PERFORM BORRAR
              WHEN "ST<"  PERFORM STMENOR
              WHEN "ST>"  PERFORM STMAYOR
              WHEN "STN<" PERFORM STNOMENOR
              WHEN "STN>" PERFORM STNOMAYOR
              WHEN "NEX"  PERFORM LEERNEXT
              WHEN "PRE"  PERFORM LEERPREVIOUS
              WHEN "UNL"  PERFORM NOBLOC
              WHEN "CLO"  PERFORM CERRAR
              WHEN "SIL"  PERFORM SETIGNORELOCK
              WHEN "GER"  PERFORM GETERRORES
              WHEN "GETALL" PERFORM GETALL
              WHEN "SETALL" PERFORM SETALL
              WHEN "STP" PERFORM TERMINA
           END-EVALUATE.
       FIN-PROCESOS.
           EXIT PROGRAM.
       FIN-PROGRAMA.
           STOP RUN.

       TERMINA SECTION.
       TERMI.
           MOVE 5 TO LONGI.
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING "|FIN|" LONGI
           ELSE
              DISPLAY "|FIN|" UPON SYSOUT
           END-IF.
           STOP RUN.
       FIN-TERMINA.

       COGE-TRABAJO SECTION.
       COG-TRA.
           IF ESCRIBIBLE = "P"
              CALL "CogeTrabajo" USING OPERACION VALORES PALPRUPARJ
           ELSE
              UNSTRING BUFERENT DELIMITED BY SEPARADOR INTO
                    OPERACION
                    VALORES
              END-UNSTRING
           END-IF.
       FIN-COGE-TRABAJO.

       ABREINPUT SECTION.
       ABR-INP.
           IF ABIERTO = 1
              GO TO FIN-ABREINPUT
           END-IF.
           IF ABIERTO = 2 OR 3
              PERFORM CERRAR
           END-IF.
           OPEN INPUT FSGNODO.
           IF ERRORES = "35" OR
             (ERROR-1 = "9" AND ERROR-2N = 9)
              PERFORM ABREOUTPUT
              PERFORM CERRAR
              GO TO ABR-INP
           END-IF.
           MOVE 1 TO ABIERTO.
       FIN-ABREINPUT.

       ABREIO SECTION.
       ABR-IO.
           IF ABIERTO = 2
              GO TO FIN-ABREIO
           END-IF.
           IF ABIERTO = 1 OR 3
              PERFORM CERRAR
           END-IF.
           OPEN I-O FSGNODO.
           IF ERRORES = "35" OR
              ERROR-1 = "9" AND ERROR-2N = 9
              PERFORM ABREOUTPUT
              PERFORM CERRAR
              GO TO ABR-IO.
           MOVE 2 TO ABIERTO.
       FIN-ABREIO.

       ABREOUTPUT SECTION.
       ABR-OUT.
           IF ABIERTO = 3
              GO TO FIN-ABREOUTPUT
           END-IF.
           IF ABIERTO = 1 OR 2
              PERFORM CERRAR
           END-IF.
           OPEN OUTPUT FSGNODO.
           IF ERRORES = "35" OR
             (ERROR-1 = "9" AND ERROR-2N = 9)
              PERFORM HACDIR
              OPEN OUTPUT FSGNODO
              IF ERRORES NOT = "00"
                 CALL "PRUERR" USING
                      ERRORES
                      NOMBREFSGNODO
                     "ASIGNADOR"
                 GO TO FIN-ABREOUTPUT.
           MOVE 3 TO ABIERTO.
       FIN-ABREOUTPUT.

       CERRAR SECTION.
       CER.
           IF ABIERTO < 1 OR > 3
              GO TO FIN-CERRAR
           END-IF.
           CLOSE FSGNODO.
           MOVE 4 TO ABIERTO.
       FIN-CERRAR.

       NOBLOC SECTION.
       CER.
           IF ABIERTO < 1 OR > 3
              GO TO FIN-NOBLOC
           END-IF.
           UNLOCK FSGNODO.
       FIN-NOBLOC.

       LEER SECTION.
       LEER-PR.
           PERFORM MOVEJTOC.
           MOVE 0 TO INVALIDKEY.
           IF ABIERTO < 1 OR > 3
              PERFORM ABREINPUT
           END-IF.
           IF ABIERTO = 3
              PERFORM CERRAR
              PERFORM ABREINPUT
           END-IF.
           IF IGNORELOCK = 1
              READ FSGNODO WITH IGNORE LOCK INVALID KEY
                   MOVE 1 TO INVALIDKEY
                   GO TO FIN-LEER
           ELSE
              READ FSGNODO INVALID KEY
                   MOVE 1 TO INVALIDKEY
                   GO TO FIN-LEER
           END-IF.
           IF ERROR-1 NOT = "0"
              MOVE 2 TO INVALIDKEY
              GO TO FIN-LEER
           END-IF.
           PERFORM MOVECTOJ.
       FIN-LEER.
           MOVE 1 TO LONGI
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING INVALIDKEY LONGI
           ELSE
              DISPLAY INVALIDKEY UPON SYSOUT
           END-IF.

       ESCRIBIR SECTION.
       ESCR.
           PERFORM MOVEJTOC.
           MOVE 0 TO INVALIDKEY.
           IF ABIERTO < 1 OR > 3
              PERFORM ABREIO
           END-IF.
           IF ABIERTO = 1
              PERFORM CERRAR
              PERFORM ABREIO
           END-IF.
           WRITE RSGNODO INVALID KEY
                 MOVE 1 TO INVALIDKEY
                 GO TO FIN-ESCRIBIR.
           IF ERROR-1 NOT = "0"
              MOVE 2 TO INVALIDKEY
              GO TO FIN-ESCRIBIR
           END-IF.
           IF TRAZAR = "true"
              MOVE "A" TO ABM
              MOVE RSGNODO TO REGISTRO
              CALL "SRUTRAZA" USING
                   NOMBREFSGNODO
                   ABM
                   REGISTRO
           END-IF.
       FIN-ESCRIBIR.
           MOVE 1 TO LONGI
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING INVALIDKEY LONGI
           ELSE
              DISPLAY INVALIDKEY UPON SYSOUT
           END-IF.

       REESCRIBIR SECTION.
       REESCR.
           PERFORM MOVEJTOC.
           MOVE 0 TO INVALIDKEY.
           IF ABIERTO < 1 OR > 3
              PERFORM ABREIO
           END-IF.
           IF ABIERTO = 1 OR 3
              PERFORM CERRAR
              PERFORM ABREIO
           END-IF.
           REWRITE RSGNODO INVALID KEY
                   MOVE 1 TO INVALIDKEY
                   GO TO FIN-REESCRIBIR.
           IF ERROR-1 NOT = "0"
              MOVE 2 TO INVALIDKEY
              GO TO FIN-REESCRIBIR
           END-IF.
           IF TRAZAR = "true"
              MOVE "M" TO ABM
              MOVE RSGNODO TO REGISTRO
              CALL "SRUTRAZA" USING
                   NOMBREFSGNODO
                   ABM
                   REGISTRO
           END-IF.
       FIN-REESCRIBIR.
           MOVE 1 TO LONGI
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING INVALIDKEY LONGI
           ELSE
              DISPLAY INVALIDKEY UPON SYSOUT
           END-IF.

       BORRAR SECTION.
       BO.
           PERFORM MOVEJTOC.
           MOVE 0 TO INVALIDKEY.
           IF ABIERTO < 1 OR > 3
              PERFORM ABREIO
           END-IF.
           IF ABIERTO = 1 OR 3
              PERFORM CERRAR
              PERFORM ABREIO
           END-IF.
           DELETE FSGNODO INVALID KEY
                  MOVE 1 TO INVALIDKEY
                  GO TO FIN-BORRAR.
           IF ERROR-1 NOT = "0"
              MOVE 2 TO INVALIDKEY
              GO TO FIN-BORRAR
           END-IF.
           IF TRAZAR = "true"
              MOVE "B" TO ABM
              MOVE RSGNODO TO REGISTRO
              CALL "SRUTRAZA" USING
                   NOMBREFSGNODO
                   ABM
                   REGISTRO
           END-IF.
       FIN-BORRAR.
           MOVE 1 TO LONGI
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING INVALIDKEY LONGI
           ELSE
              DISPLAY INVALIDKEY UPON SYSOUT
           END-IF.

       STMENOR SECTION.
       STME.
           PERFORM MOVEJTOC.
           MOVE 0 TO INVALIDKEY.
           MOVE 0 TO INVALIDKEYNEXT.
           MOVE 0 TO INVALIDKEYPREV.
           IF ABIERTO < 1 OR > 3
              PERFORM ABREINPUT
           END-IF.
           IF ABIERTO = 3
              PERFORM CERRAR
              PERFORM ABREINPUT
           END-IF.
           MOVE 0 TO INVALIDKEY.
           START FSGNODO KEY < CLA INVALID KEY
                 MOVE 1 TO INVALIDKEY
                 GO TO FIN-STMENOR.
           IF ERROR-1 NOT = "0"
              MOVE 2 TO INVALIDKEY
              GO TO FIN-STMENOR
           END-IF.
       FIN-STMENOR.
           MOVE 1 TO LONGI
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING INVALIDKEY LONGI
           ELSE
              DISPLAY INVALIDKEY UPON SYSOUT
           END-IF.
           MOVE INVALIDKEY TO INVALIDKEYNEXT.
           MOVE INVALIDKEY TO INVALIDKEYPREV.

       STMAYOR SECTION.
       STMA.
           PERFORM MOVEJTOC.
           MOVE 0 TO INVALIDKEY.
           MOVE 0 TO INVALIDKEYNEXT.
           MOVE 0 TO INVALIDKEYPREV.
           IF ABIERTO < 1 OR > 3
              PERFORM ABREINPUT
           END-IF.
           IF ABIERTO = 3
              PERFORM CERRAR
              PERFORM ABREINPUT
           END-IF.
           MOVE 0 TO INVALIDKEY.
           START FSGNODO KEY > CLA INVALID KEY
                 MOVE 1 TO INVALIDKEY
                 GO TO FIN-STMAYOR.
           IF ERROR-1 NOT = "0"
              MOVE 2 TO INVALIDKEY
              GO TO FIN-STMAYOR
           END-IF.
       FIN-STMAYOR.
           MOVE 1 TO LONGI
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING INVALIDKEY LONGI
           ELSE
              DISPLAY INVALIDKEY UPON SYSOUT
           END-IF.
           MOVE INVALIDKEY TO INVALIDKEYNEXT.
           MOVE INVALIDKEY TO INVALIDKEYPREV.

       STNOMENOR SECTION.
       STANOME.
           PERFORM MOVEJTOC.
           MOVE 0 TO INVALIDKEY.
           MOVE 0 TO INVALIDKEYNEXT.
           MOVE 0 TO INVALIDKEYPREV.
           IF ABIERTO < 1 OR > 3
              PERFORM ABREINPUT
           END-IF.
           IF ABIERTO = 3
              PERFORM CERRAR
              PERFORM ABREINPUT
           END-IF.
           MOVE 0 TO INVALIDKEY.
           START FSGNODO KEY NOT < CLA INVALID KEY
                 MOVE 1 TO INVALIDKEY
                 GO TO FIN-STNOMENOR.
           IF ERROR-1 NOT = "0"
              MOVE 2 TO INVALIDKEY
              GO TO FIN-STNOMENOR
           END-IF.
       FIN-STNOMENOR.
           MOVE 1 TO LONGI
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING INVALIDKEY LONGI
           ELSE
              DISPLAY INVALIDKEY UPON SYSOUT
           END-IF.
           MOVE INVALIDKEY TO INVALIDKEYNEXT.
           MOVE INVALIDKEY TO INVALIDKEYPREV.

       STNOMAYOR SECTION.
       STANOMA.
           PERFORM MOVEJTOC.
           MOVE 0 TO INVALIDKEY.
           MOVE 0 TO INVALIDKEYNEXT.
           MOVE 0 TO INVALIDKEYPREV.
           IF ABIERTO < 1 OR > 3
              PERFORM ABREINPUT
           END-IF.
           IF ABIERTO = 3
              PERFORM CERRAR
              PERFORM ABREINPUT
           END-IF.
           MOVE 0 TO INVALIDKEY.
           START FSGNODO KEY NOT > CLA INVALID KEY
                 MOVE 1 TO INVALIDKEY
                 GO TO FIN-STNOMAYOR.
           IF ERROR-1 NOT = "0"
              MOVE 2 TO INVALIDKEY
              GO TO FIN-STNOMAYOR
           END-IF.
       FIN-STNOMAYOR.
           MOVE 1 TO LONGI
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING INVALIDKEY LONGI
           ELSE
              DISPLAY INVALIDKEY UPON SYSOUT
           END-IF.
           MOVE INVALIDKEY TO INVALIDKEYNEXT.
           MOVE INVALIDKEY TO INVALIDKEYPREV.

       LEERNEXT SECTION.
       LENE.
           MOVE 0 TO INVALIDKEY.
           MOVE 0 TO INVALIDKEYPREV.
           IF INVALIDKEYNEXT = 1
              MOVE 1 TO INVALIDKEY
              GO TO FIN-LEERNEXT
           END-IF.
           IF IGNORELOCK = 1
              READ FSGNODO NEXT RECORD WITH IGNORE LOCK AT END
                   MOVE 1 TO INVALIDKEY
                   GO TO FIN-LEERNEXT
           ELSE
              READ FSGNODO NEXT RECORD AT END
                   MOVE 1 TO INVALIDKEY
                   GO TO FIN-LEERNEXT
           END-IF.
           IF ERROR-1 NOT = "0"
              MOVE 2 TO INVALIDKEY
              GO TO FIN-LEERNEXT
           END-IF.
           PERFORM MOVECTOJ.
       FIN-LEERNEXT.
           MOVE 1 TO LONGI
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING INVALIDKEY LONGI
           ELSE
              DISPLAY INVALIDKEY UPON SYSOUT
           END-IF.
           MOVE INVALIDKEY TO INVALIDKEYNEXT.

       LEERPREVIOUS SECTION.
       LEPR.
           MOVE 0 TO INVALIDKEY.
           MOVE 0 TO INVALIDKEYNEXT.
           IF INVALIDKEYPREV = 1
              MOVE 1 TO INVALIDKEY
              GO TO FIN-LEERPREVIOUS
           END-IF.
           IF IGNORELOCK = 1
              READ FSGNODO PREVIOUS RECORD WITH IGNORE LOCK AT END
                   MOVE 1 TO INVALIDKEY
                   GO TO FIN-LEERPREVIOUS
           ELSE
              READ FSGNODO PREVIOUS RECORD AT END
                   MOVE 1 TO INVALIDKEY
                   GO TO FIN-LEERPREVIOUS
           END-IF.
           IF ERROR-1 NOT = "0"
              MOVE 2 TO INVALIDKEY
              GO TO FIN-LEERPREVIOUS
           END-IF.
           PERFORM MOVECTOJ.
       FIN-LEERPREVIOUS.
           MOVE 1 TO LONGI
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING INVALIDKEY LONGI
           ELSE
              DISPLAY INVALIDKEY UPON SYSOUT
           END-IF.
           MOVE INVALIDKEY TO INVALIDKEYPREV.

       HACDIR SECTION.
       HACIENDODIR.
           MOVE SPACES TO VALORDIR.
           MOVE SPACES TO NOMBRDIR.
           STRING "dd_" NOMBREFSGNODO(1:3)
                  DELIMITED BY " "
                  INTO NOMBRDIR
           END-STRING.
           DISPLAY NOMBRDIR UPON ENVIRONMENT-NAME.
           ACCEPT VALORDIR FROM ENVIRONMENT-VALUE.
           IF VALORDIR NOT = SPACES AND
              VALORDIR NOT = LOW-VALUES
              MOVE SPACES TO BUFER-DIR
              STRING VALORDIR NOMBREFSGNODO(4:)
                     DELIMITED BY " "
                     INTO BUFER-DIR
              END-STRING
           ELSE
              MOVE NOMBREFSGNODO TO BUFER-DIR
           END-IF.
           MOVE SPACES TO DIRECTORIO.
           PERFORM VARYING II FROM 1 BY 1 UNTIL II > 256
                   IF BUF-1(II) = "/" OR "\"
                      CALL "CBL_CREATE_DIR" USING DIRECTORIO
                   END-IF
                   MOVE BUF-1(II) TO DIR-1(II)
                   IF DIR-1(II) = " "
                      MOVE 256 TO II
                   END-IF
           END-PERFORM.
       FIN-HACDIR.


       PONNOMBRE SECTION.
       PON.
           MOVE VALORES TO NOMBREFSGNODO.
       FIN-PONNOMBRE.

       SETIGNORELOCK SECTION.
       PON.
           MOVE "1" TO IGNORELOCK.
           IF VALORES(1:1) = "0"
              MOVE "0" TO IGNORELOCK.
       FIN-IGNORELOCK.

       GETERRORES SECTION.
       GON.
           MOVE 2 TO LONGI
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING ERRORES LONGI
           ELSE
              DISPLAY ERRORES UPON SYSOUT
           END-IF.


       GETALL SECTION.
       GT.
           MOVE 128 TO LONGI
           IF ESCRIBIBLE = "P"
              CALL "Escribir" USING JRSGNODO LONGI
           ELSE
              DISPLAY JRSGNODO UPON SYSOUT
           END-IF.
       FIN-GETALL.

       SETALL SECTION.
       ST.
           MOVE VALORES TO JRSGNODO.
       FIN-SETALL.

       MOVEJTOC SECTION.
       MJTOC.
           MOVE JCLA TO CLA.
       FIN-MOVEJTOC.

       MOVECTOJ SECTION.
       MCTOJ.
           MOVE CLA TO JCLA.
       FIN-MOVECTOJ.

       CARGATODOS SECTION.
       INI-CARGA.
           IF ABIERTO < 1 OR > 3
              PERFORM ABREINPUT
           END-IF.
           IF ABIERTO = 3
              PERFORM CERRAR
              PERFORM ABREINPUT
           END-IF.
           DISPLAY "|INI-MUCHOS|" UPON SYSOUT.
           INITIALIZE RSGNODO.
           IF VALORES = SPACES
              START FSGNODO
                 KEY NOT < CLA
                 INVALID KEY
                 GO TO FIN-CARGATODOS
           END-IF.
       LEE-CARGA.
           READ FSGNODO NEXT RECORD WITH IGNORE LOCK
                AT END GO TO FIN-CARGATODOS.
           PERFORM MOVECTOJ.
           PERFORM GETALL.
           GO TO LEE-CARGA.
       FIN-CARGATODOS.
           DISPLAY "|FIN-MUCHOS|" UPON SYSOUT.
