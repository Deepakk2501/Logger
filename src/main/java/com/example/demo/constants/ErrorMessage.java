package com.example.demo.constants;

public enum ErrorMessage {
     ADMIN_MESSAGE {
         @Override
         public String getValue() {
         return "send alert to admin";
         }
     },
    DEVELOPER {
        @Override
        public String getValue() {
            return "send alert to developer";
        }
    };

    public String getValue() {
        return "default";
    };

}
