package io.github.heraclessupport;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.jsonSchema.extension.JsonSchemaFileProvider;
import com.jetbrains.jsonSchema.extension.JsonSchemaProviderFactory;
import com.jetbrains.jsonSchema.extension.SchemaType;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SchemaProvider implements JsonSchemaProviderFactory {
    @Override
    public @NotNull List<JsonSchemaFileProvider> getProviders(@NotNull Project project) {
        return List.of(new SchemaFileProvider(project));
    }

    class SchemaFileProvider implements JsonSchemaFileProvider {
        private final Project project;

        public SchemaFileProvider(Project project) {
            this.project = project;
        }

        @Override
        public boolean isAvailable(@NotNull VirtualFile file) {
            return file.getName().endsWith(".json") && file.getPath().contains("/heracles/quests/");
        }

        @Override
        public @NotNull @Nls String getName() {
            return "Heracles";
        }

        @Override
        public VirtualFile getSchemaFile() {
            return JsonSchemaProviderFactory.getResourceFile(SchemaProvider.class, "/schema.json");
        }

        @Override
        public @NotNull SchemaType getSchemaType() {
            return SchemaType.embeddedSchema;
        }
    }
}
