        IDENTIFICATION DIVISION.
        PROGRAM-ID.    ASGUR.
        SECURITY.      ASIGNADOR.

        ENVIRONMENT DIVISION.

        INPUT-OUTPUT SECTION.
        FILE-CONTROL.
            COPY "FSGUR-SEL".
        DATA DIVISION.
        FILE SECTION.
            COPY "FSGUR-FD".
        WORKING-STORAGE SECTION.
           COPY "WO-ERRORES".
        LINKAGE SECTION.
        01 NOMBREFSGUR PIC X(256).
        PROCEDURE DIVISION USING
                  NOMBREFSGUR.
        PROGRAMA SECTION.
        PROCESOS.
            IF NOMBREFSGUR = SPACES OR LOW-VALUES
               MOVE "mnu/SG/FSGUR" TO NOMBREFSGUR
            END-IF.
            CALL "PRUMKDIR" USING NOMBREFSGUR.
            OPEN OUTPUT FSGUR.
            IF ERRORES NOT = "00"
               CALL "PRUERR" USING
                    ERRORES
                    NOMBREFSGUR
                   "ASIGNADOR"
               GO TO FIN-PROGRAMA.
            CLOSE FSGUR.
        FIN-PROCESOS.
            EXIT PROGRAM.
        FIN-PROGRAMA.
            STOP RUN.
