        IDENTIFICATION DIVISION.
        PROGRAM-ID.    TSGSER.
        SECURITY.      REORGANIZA.

        ENVIRONMENT DIVISION.

        INPUT-OUTPUT SECTION.
        FILE-CONTROL.

            COPY "FSGSER-SEL".

            SELECT FOLD
                   ASSIGN TO RANDOM NOMBREOLD
                   ORGANIZATION IS LINE SEQUENTIAL
                   ACCESS MODE IS SEQUENTIAL
                   FILE STATUS IS ERRORES.

        DATA DIVISION.
        FILE SECTION.

            COPY "FSGSER-FD".

        FD  FOLD
            DATA RECORD IS ROLD.
        01  ROLD       PIC X(4096).

        WORKING-STORAGE SECTION.

            COPY "WO-ERRORES".

        77  PROG PIC X(128)  VALUE "TSGSER".
        77  REGL PIC 9(8).
        77  REGD PIC 9(8).
        77  REGG PIC 9(8).
        77  NLIN PIC 9(8).

        LINKAGE SECTION.
        01 NOMBREFSGSER PIC X(256).
        PROCEDURE DIVISION USING
                  NOMBREFSGSER.
        DECLARATIVES.
            COPY "FSGSER-DEC".
        END DECLARATIVES.

        PROGRAMA SECTION.
        INICIALES.
            IF NOMBREFSGSER = SPACES OR LOW-VALUES
               MOVE "mnu/SG/FSGSER" TO NOMBREFSGSER
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
            STRING NOMBREFSGSER "OLD"
                   DELIMITED BY " "
                   INTO NOMBREOLD.
*********** ATENCION:YA DEBER�A ESTAR MOVIDO A OLD.
*********** DESDE JAVA YA SE HACE. SI LO EST�S LLAMANDO A MANO DEBES HABERLO MOVIDO A OLD ANTES
            OPEN INPUT FOLD.
            IF ERRORES NOT = "00" *>SI EL OLD NO EXISTE HAY QUE HACERLO.
               DISPLAY "HAY QUE HACER EL " NOMBREOLD UPON SYSOUT
               DISPLAY "ANTES DE LLAMAR A �STE" UPON SYSOUT
               EXIT PROGRAM
               STOP RUN
            END-IF.
            CLOSE FOLD.
        FIN-MOVIDA.
            EXIT.

        ASIGNACION SECTION.
        ASIG.
            CALL "ASGSER" USING NOMBREFSGSER.
        FIN-ASIGNACION.
            EXIT.

        GRABACION SECTION.
        ABRIR.
            MOVE ZEROES TO NLIN.
            MOVE SPACES TO ERRORES.
            PERFORM UNTIL ERROR-1 = "0"
               OPEN I-O FSGSER
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
            MOVE ROLD TO RSGSER
            READ FSGSER INVALID KEY
                 GO TO GRABA-BIEN.
        GRABA-MAL.
            ADD 1 TO REGD.
            IF NLIN = 1
               DISPLAY "DEBUG:DUPLICADOS:" REGD UPON SYSOUT.
            GO TO LEER.
        GRABA-BIEN.
            WRITE RSGSER INVALID KEY GO TO GRABA-MAL.
            ADD 1 TO REGG.
            IF NLIN = 1
               DISPLAY "DEBUG:GRABADOS:" REGG UPON SYSOUT.
            GO TO LEER.
        CERRAR.
            DISPLAY "DEBUG:LEIDOS:" REGL UPON SYSOUT.
            DISPLAY "DEBUG:GRABADOS:" REGG UPON SYSOUT.
            DISPLAY "DEBUG:DUPLICADOS:" REGD UPON SYSOUT.
            CLOSE FSGSER.
            CLOSE FOLD.
        FIN-GRABACION.
            EXIT.

        BORRADO SECTION.
        BORROLD.
            CALL "PRURM" USING NOMBREOLD.
        FIN-BORRADO.
            EXIT.
