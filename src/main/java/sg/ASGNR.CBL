        IDENTIFICATION DIVISION.
        PROGRAM-ID.    ASGNR.
        SECURITY.      ASIGNADOR.

        ENVIRONMENT DIVISION.

        INPUT-OUTPUT SECTION.
        FILE-CONTROL.
            COPY "FSGNR-SEL".
        DATA DIVISION.
        FILE SECTION.
            COPY "FSGNR-FD".
        WORKING-STORAGE SECTION.
           COPY "WO-ERRORES".
        LINKAGE SECTION.
        01 NOMBREFSGNR PIC X(256).
        PROCEDURE DIVISION USING
                  NOMBREFSGNR.
        PROGRAMA SECTION.
        PROCESOS.
            IF NOMBREFSGNR = SPACES OR LOW-VALUES
               MOVE "mnu/SG/FSGNR" TO NOMBREFSGNR
            END-IF.
            CALL "PRUMKDIR" USING NOMBREFSGNR.
            OPEN OUTPUT FSGNR.
            IF ERRORES NOT = "00"
               CALL "PRUERR" USING
                    ERRORES
                    NOMBREFSGNR
                   "ASIGNADOR"
               GO TO FIN-PROGRAMA.
            CLOSE FSGNR.
        FIN-PROCESOS.
            EXIT PROGRAM.
        FIN-PROGRAMA.
            STOP RUN.
