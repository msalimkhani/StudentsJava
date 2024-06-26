package com.salimkhani.tools;

public class Property <T> {
    private T _t;
    public final T get()
    {
        if(_t != null)
            return _t;
        else
            throw new NullPointerException("Property: Null Reference Type!");
    }
    public Property(){};
    public Property(T val)
    {
        this._t = val;
    }
    public final void set(T val)
    {
        this._t = val;
    }
}
