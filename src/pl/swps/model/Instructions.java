package pl.swps.model;

public class Instructions {
    public String messageS1_taskInstructions;
    public String messageS2_taskProcedureButtons;
    public String messageS3_writeResults;
    public String messageS4_nextTaskProcedureButtons;
    public String messageS5_thanks;

    public Instructions() {
        this.messageS1_taskInstructions = "Za chwilę rozpocznie się zadanie komputerowe. " +
                "Na ekranie będą wyświetlane po kolei słowa. Twoim zadaniem będzie zapamiętanie jak największej ich liczby. " +
                "Naciśnij spację, aby przejść dalej.";
        this.messageS2_taskProcedureButtons = "Naciśnij spację, aby rozpocząć zadanie.";
        this.messageS3_writeResults = "Proszę teraz wypisać na kartce wszystkie zapamiętane słowa.\n" +
                "Kolejność słów nie ma znaczenia.\n" +
                "Po wypisaniu słów, proszę nacisnąć spację.";
        this.messageS4_nextTaskProcedureButtons = "Naciśnij spację, aby rozpocząć kolejne zadanie.";
        this.messageS5_thanks = " Dziękujemy, to już koniec zadania komputerowego.";
    }

    public Instructions(String messageS1_taskInstructions,
                        String messageS2_taskProcedureButtons,
                        String messageS3_writeResults,
                        String messageS4_nextTaskProcedureButtons,
                        String messageS5_thanks) {
        this.messageS1_taskInstructions = messageS1_taskInstructions;
        this.messageS2_taskProcedureButtons = messageS2_taskProcedureButtons;
        this.messageS3_writeResults = messageS3_writeResults;
        this.messageS4_nextTaskProcedureButtons = messageS4_nextTaskProcedureButtons;
        this.messageS5_thanks = messageS5_thanks;
    }


    //    Screen 1: Instructions about the task
//    Screen 2: How to proceed with the task (action buttons)
//<Loop>
//    Screen 3: Loop of words ...
//    Screen 4: Instructions: Write the results in a paper
//    Screen 5: How to proceed with the next task (action buttons)
//</Loop>
//    Screen 6: Final Instructions: Write the results in a paper
//    Screen 7: Thank you note
}
