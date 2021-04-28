package liquibase.command.core;

import liquibase.command.*;
import liquibase.integration.commandline.Main;

public class ChangeLogSyncSQLCommandStep extends AbstractCliWrapperCommandStep {
    public static final CommandArgumentDefinition<String> CHANGELOG_FILE_ARG;
    public static final CommandArgumentDefinition<String> URL_ARG;
    public static final CommandArgumentDefinition<String> USERNAME_ARG;
    public static final CommandArgumentDefinition<String> PASSWORD_ARG;
    public static final CommandArgumentDefinition<String> LABELS_ARG;
    public static final CommandArgumentDefinition<String> CONTEXTS_ARG;

    static {
        CommandStepBuilder builder = new CommandStepBuilder(ChangeLogSyncSQLCommandStep.class);
        CHANGELOG_FILE_ARG = builder.argument("changeLogFile", String.class).required()
            .description("The root changelog file").build();
        URL_ARG = builder.argument("url", String.class).required()
            .description("The JDBC database connection URL").build();
        USERNAME_ARG = builder.argument("username", String.class)
            .description("The database username").build();
        PASSWORD_ARG = builder.argument("password", String.class)
            .description("The database password").build();
        LABELS_ARG = builder.argument("labels", String.class)
            .description("Label expression to use for filtering which changes to mark as executed").build();
        CONTEXTS_ARG = builder.argument("contexts", String.class)
            .description("Context string to use for filtering which changes to mark as executed").build();
    }

    @Override
    public String[] getName() {
        return new String[] {"changeLogSyncSQL"};
    }

    @Override
    public void run(CommandResultsBuilder resultsBuilder) throws Exception {
        CommandScope commandScope = resultsBuilder.getCommandScope();

        String[] args = createArgs(commandScope);
        int statusCode = Main.run(args);
        addStatusMessage(resultsBuilder, statusCode);
        resultsBuilder.addResult("statusCode", statusCode);
    }

    @Override
    public void adjustCommandDefinition(CommandDefinition commandDefinition) {
        commandDefinition.setShortDescription("Output the raw SQL used by Liquibase when running changeLogSync");
        commandDefinition.setLongDescription("Output the raw SQL used by Liquibase when running changeLogSync");
    }
}
