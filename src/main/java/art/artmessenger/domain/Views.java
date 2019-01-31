package art.artmessenger.domain;

public final class Views {
    public interface Id{} // Поля помеченные в Entity этим интервейсом будут выводиться в сообщениях

    public interface IdName extends Id{} // Этот будет выводить поля помеченные им и родителем

    public interface FullMessage extends Id{}
}
