package client.messagehandlers;

import client.IUILogic;

public abstract class BaseMessageHandler {
    IUILogic logic;
    BaseMessageHandler(IUILogic logic){
        this.logic = logic;
    }
}
