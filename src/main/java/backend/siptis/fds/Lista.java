package backend.siptis.fds;

public interface Lista <T>
{
    public void add(T dato);

    public T add(T dato, int pos);

    public T remove(T dato);

    public T remove(int pos);

    public int size();

    public T get(int pos);

    public T set(T dato, int pos);
}
