package pl.swps.model;

public class InstructionMessages {
    public String messageS1_taskInstructions;
    public String messageS2_taskProcedureButtons;
    public String messageS3_writeResults;
    public String messageS4_nextTaskProcedureButtons;
    public String messageS5_thanks;

    public InstructionMessages() {
        this.messageS1_taskInstructions = "Za chwilę zobaczysz listę 15 słów. " +
                "Postaraj się je zapamiętać w kolejności, w której były pokazywane. " +
                "Po wyświetleniu słów, Twoim zadaniem będzie zapisanie ich na kartce. \n" +
                "Naciśnij spację, aby przejść dalej.";
        this.messageS2_taskProcedureButtons = "Naciśnij spację, aby rozpocząć zadanie.";
        this.messageS3_writeResults = "Postaraj się teraz wypisać na kartce wszystkie zapamiętane słowa, " +
                "w miarę możliwości w takiej kolejności, w której były pokazywane.\n" +
                "Po wypisaniu słów, proszę nacisnąć spację.";
        this.messageS4_nextTaskProcedureButtons = "Naciśnij spację, aby rozpocząć kolejne zadanie.";
        this.messageS5_thanks = "Dziękujemy, to już koniec zadania komputerowego.";
    }

}
