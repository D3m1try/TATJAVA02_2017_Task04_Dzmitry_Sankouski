package com.epam.news_manager.controller.impl;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public enum CommandName {
    ADD{
        public final String USAGE = "[book,disk or movie] [-key] [val]";

        @Override
        public String getUsage() {
            return USAGE;
        }

    },
    FIND{
        public final String USAGE = "[book,disk or movie] [-p  for precise search] [field to search] [what to search]";

        @Override
        public String getUsage() {
            return USAGE;
        }
    },
    NON_EXISTING {
        public final String USAGE = "doesn't exist";

        @Override
        public String getUsage() {
            return USAGE;
        }
    },
    EXIT{
        public final String USAGE = "";

        @Override
        public String getUsage() {
            return USAGE;
        }
    },
    HELP{
        public final String USAGE = "[command]";

        @Override
        public String getUsage() {
            return USAGE;
        }
    };

    public abstract String getUsage();
}
