package com.gmail.luxdevpl.jointicket.storage;

import com.gmail.luxdevpl.jointicket.MainClass;
import com.gmail.luxdevpl.jointicket.configuration.TicketConfiguration;
import com.gmail.luxdevpl.jointicket.storage.util.IDatabase;
import org.apache.commons.lang3.Validate;

public abstract class AbstractDatabase implements IDatabase {

    private final MainClass mainInstance;

    public AbstractDatabase(MainClass mainInstance) {
        Validate.notNull(mainInstance);

        this.mainInstance = mainInstance;
    }

    @Override
    public void open() {
        this.create(mainInstance.getConfiguration());
    }

    @Override
    public void close() {
        this.destroy(mainInstance.getConfiguration());
    }

    protected abstract void create(TicketConfiguration configuration);

    protected abstract void destroy(TicketConfiguration configuration);

}
