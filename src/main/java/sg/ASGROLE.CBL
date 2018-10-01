        IDENTIFICATION DIVISION.
        PROGRAM-ID.    ASGROLE.
        SECURITY.      ASIGNADOR.

        ENVIRONMENT DIVISION.

        INPUT-OUTPUT SECTION.
        FILE-CONTROL.
            COPY "FSGROLE-SEL".
        DATA DIVISION.
        FILE SECTION.
            COPY "FSGROLE-FD".
        WORKING-STORAGE SECTION.
           COPY "WO-ERRORES".
        LINKAGE SECTION.
        01 NOMBREFSGROLE PIC X(256).
        PROCEDURE DIVISION USING
                  NOMBREFSGROLE.
        PROGRAMA SECTION.
        PROCESOS.
            IF NOMBREFSGROLE = SPACES OR LOW-VALUES
               MOVE "mnu/SG/FSGROLE" TO NOMBREFSGROLE
            END-IF.
            CALL "PRUMKDIR" USING NOMBREFSGROLE.
            OPEN OUTPUT FSGROLE.
            IF ERRORES NOT = "00"
               CALL "PRUERR" USING
                    ERRORES
                    NOMBREFSGROLE
                   "ASIGNADOR"
               GO TO FIN-PROGRAMA.
            CLOSE FSGROLE.
        FIN-PROCESOS.
            EXIT PROGRAM.
        FIN-PROGRAMA.
            STOP RUN.
