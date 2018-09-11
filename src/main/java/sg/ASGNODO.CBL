        IDENTIFICATION DIVISION.
        PROGRAM-ID.    ASGNODO.
        SECURITY.      ASIGNADOR.
        INPUT-OUTPUT SECTION.
        FILE-CONTROL.
            COPY "FSGNODO-SEL".
        DATA DIVISION.
        FILE SECTION.
            COPY "FSGNODO-FD".
        WORKING-STORAGE SECTION.
           COPY "WO-ERRORES".
        LINKAGE SECTION.
        01 NOMBREFSGNODO PIC X(256).
        PROCEDURE DIVISION USING
                  NOMBREFSGNODO.
        PROGRAMA SECTION.
        PROCESOS.
            IF NOMBREFSGNODO = SPACES OR LOW-VALUES
               MOVE "mnu/SG/FSGNODO" TO NOMBREFSGNODO
            END-IF.
            CALL "PRUMKDIR" USING NOMBREFSGNODO.
            OPEN OUTPUT FSGNODO.
            IF ERRORES NOT = "00"
               CALL "PRUERR" USING
                    ERRORES
                    NOMBREFSGNODO
                   "ASIGNADOR"
               GO TO FIN-PROGRAMA.
            CLOSE FSGNODO.
        FIN-PROCESOS.
            EXIT PROGRAM.
        FIN-PROGRAMA.
            STOP RUN.
