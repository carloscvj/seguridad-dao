        IDENTIFICATION DIVISION.
        PROGRAM-ID.    ASGSER.
        SECURITY.      ASIGNADOR.

        ENVIRONMENT DIVISION.

        INPUT-OUTPUT SECTION.
        FILE-CONTROL.
            COPY "FSGSER-SEL".
        DATA DIVISION.
        FILE SECTION.
            COPY "FSGSER-FD".
        WORKING-STORAGE SECTION.
           COPY "WO-ERRORES".
        LINKAGE SECTION.
        01 NOMBREFSGSER PIC X(256).
        PROCEDURE DIVISION USING
                  NOMBREFSGSER.
        PROGRAMA SECTION.
        PROCESOS.
            IF NOMBREFSGSER = SPACES OR LOW-VALUES
               MOVE "mnu/SG/FSGSER" TO NOMBREFSGSER
            END-IF.
            CALL "PRUMKDIR" USING NOMBREFSGSER.
            OPEN OUTPUT FSGSER.
            IF ERRORES NOT = "00"
               CALL "PRUERR" USING
                    ERRORES
                    NOMBREFSGSER
                   "ASIGNADOR"
               GO TO FIN-PROGRAMA.
            CLOSE FSGSER.
        FIN-PROCESOS.
            EXIT PROGRAM.
        FIN-PROGRAMA.
            STOP RUN.
