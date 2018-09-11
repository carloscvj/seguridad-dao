        IDENTIFICATION DIVISION.
        PROGRAM-ID.    TSGNODO.
        SECURITY.      REORGANIZA.

        ENVIRONMENT DIVISION.

        INPUT-OUTPUT SECTION.
        FILE-CONTROL.

            COPY "FSGNODO-SEL".

            SELECT FOLD
                   ASSIGN TO RANDOM NOMBREOLD
                   ORGANIZATION IS LINE SEQUENTIAL
                   ACCESS MODE IS SEQUENTIAL
                   FILE STATUS IS ERRORES.

        DATA DIVISION.
        FILE SECTION.

            COPY "FSGNODO-FD".

        FD  FOLD
            DATA RECORD IS ROLD.
        01  ROLD       PIC X(4096).

        WORKING-STORAGE SECTION.

            COPY "WO-ERRORES".

        77  PROG PIC X(128)  VALUE "TSGNODO".
        77  REGL PIC 9(8).
        77  REGD PIC 9(8).
        77  REGG PIC 9(8).
        77  NLIN PIC 9(8).

        LINKAGE SECTION.
        01 NOMBREFSGNODO PIC X(256).
        PROCEDURE DIVISION USING
                  NOMBREFSGNODO.
        DECLARATIVES.
            COPY "FSGNODO-DEC".
        END DECLARATIVES.

        PROGRAMA SECTION.
        INICIALES.
            IF NOMBREFSGNODO = SPACES OR LOW-VALUES
               MOVE "mnu/SG/FSGNODO" TO NOMBREFSGNODO
            END-IF.
            INITIALIZE REGL REGD REGG.
        PROCESOS.
            PERFORM MOVIDA.
            PERFORM ASIGNACION.
            PERFORM GRABACION.
            PERFORM BORRADO.
        FIN-PROCESO.
            EXIT PROGRAM.
        FIN-PROGRAMA.
            STOP RUN.

        MOVIDA SECTION.
        LLA.
            MOVE SPACES TO NOMBREOLD.
            STRING NOMBREFSGNODO "OLD"
                   DELIMITED BY " "
                   INTO NOMBREOLD.
*********** ATENCION:YA DEBERÍA ESTAR MOVIDO A OLD.
*********** DESDE JAVA YA SE HACE. SI LO ESTÁS LLAMANDO A MANO DEBES HABERLO MOVIDO A OLD ANTES
            OPEN INPUT FOLD.
            IF ERRORES NOT = "00" *>SI EL OLD NO EXISTE HAY QUE HACERLO.
               DISPLAY "HAY QUE HACER EL " NOMBREOLD UPON SYSOUT
               DISPLAY "ANTES DE LLAMAR A ÉSTE" UPON SYSOUT
               EXIT PROGRAM
               STOP RUN
            END-IF.
            CLOSE FOLD.
        FIN-MOVIDA.
            EXIT.

        ASIGNACION SECTION.
        ASIG.
            CALL "ASGNODO" USING NOMBREFSGNODO.
        FIN-ASIGNACION.
            EXIT.

        GRABACION SECTION.
        ABRIR.
            MOVE ZEROES TO NLIN.
            MOVE SPACES TO ERRORES.
            PERFORM UNTIL ERROR-1 = "0"
               OPEN I-O FSGNODO
            END-PERFORM.
            OPEN INPUT FOLD.
        LEER.
            READ FOLD AT END GO TO CERRAR.
            ADD 1 TO NLIN.
            IF NLIN > 1000
               MOVE 1 TO NLIN
            END-IF.
            ADD 1 TO REGL.
            IF NLIN = 1
               DISPLAY "DEBUG:LEIDOS:" REGL UPON SYSOUT.
            IF ROLD(1:1) = LOW-VALUES
               GO TO LEER
            END-IF.
            MOVE ROLD TO RSGNODO
            READ FSGNODO INVALID KEY
                 GO TO GRABA-BIEN.
        GRABA-MAL.
            ADD 1 TO REGD.
            IF NLIN = 1
               DISPLAY "DEBUG:DUPLICADOS:" REGD UPON SYSOUT.
            GO TO LEER.
        GRABA-BIEN.
            WRITE RSGNODO INVALID KEY GO TO GRABA-MAL.
            ADD 1 TO REGG.
            IF NLIN = 1
               DISPLAY "DEBUG:GRABADOS:" REGG UPON SYSOUT.
            GO TO LEER.
        CERRAR.
            DISPLAY "DEBUG:LEIDOS:" REGL UPON SYSOUT.
            DISPLAY "DEBUG:GRABADOS:" REGG UPON SYSOUT.
            DISPLAY "DEBUG:DUPLICADOS:" REGD UPON SYSOUT.
            CLOSE FSGNODO.
            CLOSE FOLD.
        FIN-GRABACION.
            EXIT.

        BORRADO SECTION.
        BORROLD.
            CALL "PRURM" USING NOMBREOLD.
        FIN-BORRADO.
            EXIT.
