package com.gmail.luxdevpl.jointicket.configuration.util;

import org.apache.commons.lang3.Validate;
import org.diorite.cfg.system.Template;
import org.diorite.cfg.system.TemplateCreator;

import java.io.File;
import java.io.IOException;

public class ConfigUtil {

    public static <T> T loadConfig(final File file, final Class<T> implementationFile) {
        final Template<T> template = TemplateCreator.getTemplate(implementationFile);

        T config;

        if (!file.exists()) {
            try {
                try {
                    config = template.fillDefaults(implementationFile.newInstance());
                } catch (final InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException("Couldn't get access to " + implementationFile.getName() + "  constructor", e);
                }

                Validate.isTrue(file.createNewFile(), "Couldn't create " + file.getAbsolutePath() + " config file");

            } catch (final IOException e) {
                throw new RuntimeException("IO exception while creating config file: " + file.getAbsolutePath(), e);
            }
        } else {
            try {
                try {
                    config = template.load(file);
                    if (config == null) {
                        config = template.fillDefaults(implementationFile.newInstance());
                    }
                } catch (final IOException e) {
                    throw new RuntimeException("IO exception while loading config file: " + file.getAbsolutePath(), e);
                }
            } catch (final InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Couldn't get access to " + implementationFile.getName() + "  constructor", e);
            }
        }

        try {
            template.dump(file, config, false);
        } catch (final IOException e) {
            throw new RuntimeException("Can't dump configuration file!", e);
        }

        return config;
    }

}
