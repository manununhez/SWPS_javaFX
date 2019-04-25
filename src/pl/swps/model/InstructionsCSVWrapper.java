package pl.swps.model;

import java.util.List;

public class InstructionsCSVWrapper {

    private InstructionMessages instructionMessages;
    private List stringWords;

    public InstructionsCSVWrapper(List stringWords) {
        this.stringWords = stringWords;
        instructionMessages = new InstructionMessages();
    }

    public InstructionMessages getInstructionsFromCSV() {
        String[] token_taskInstructions = (String[]) stringWords.get(0);
        instructionMessages.messageS1_taskInstructions = token_taskInstructions[0];
        String[] token_taskProcedureButtons = (String[]) stringWords.get(1);
        instructionMessages.messageS2_taskProcedureButtons = token_taskProcedureButtons[0];
        String[] token_writeResults = (String[]) stringWords.get(2);
        instructionMessages.messageS3_writeResults = token_writeResults[0];
        String[] token_nextTaskProcedureButtons = (String[]) stringWords.get(3);
        instructionMessages.messageS4_nextTaskProcedureButtons = token_nextTaskProcedureButtons[0];
        String[] token5_thanks = (String[]) stringWords.get(4);
        instructionMessages.messageS5_thanks = token5_thanks[0];

        return instructionMessages;
    }
}
