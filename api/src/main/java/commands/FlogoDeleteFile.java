package commands;

import api.ResponseBuilder;
import concepts.Concept;
import concepts.ServerFilesManager;
import spark.Request;

public class FlogoDeleteFile implements Command {

    private static final String NAME_PARAMETER = "name";

    private final ServerFilesManager serverFilesManager;
    private final Concept concept;

    public FlogoDeleteFile(ServerFilesManager serverFilesManager, Concept concept) {
        this.serverFilesManager = serverFilesManager;
        this.concept = concept;
    }

    @Override
    public String execute(Request request, ResponseBuilder responseBuilder) {
        if (serverFilesManager.deleteConcept(concept.name(extractName(request))))
            return responseBuilder.successResponse("File delete");
        return responseBuilder.errorResponse("This file was not on the server");
    }

    private String extractName(Request request) {
        return request.params(NAME_PARAMETER);
    }

}