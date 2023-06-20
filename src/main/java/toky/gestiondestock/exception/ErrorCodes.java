package toky.gestiondestock.exception;

public enum ErrorCodes {
    ARTICLE_NOT_FOUND(1),
    COMMNDE_CLIENT_NOT_FOUND(2),
    CATEGORY_NOT_FOUND(3),
    CLIENT_NOT_FOUND(4),
    UTILISATEUR_NOT_FOUND(5),
    VENTE_NOT_FOUND(6),
    MVT_STOCK_NOT_FOUND(7),
    ENTREPRISE_NOT_FOUND(8),
    LIGNE_COMMANDE_CLIENT_NOT_FOUND(9),
    LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND(10),
    LIGNE_VENTE_NOT_FOUND(11),
    FOURNISSEUR_NOT_FOUND(12),
    FOURNISSEUR_NOT_VALID(20),
    ARTICLE_NOT_VALID(13),
    COMMANDE_CLIENT_NOT_VALID(14),
    COMMANDE_FOUNISSEUR_NOT_FOUND(15),
    VENTE_IS_NOT_VALID(16),
    CATEGORIE_NOT_VALID(17),
    CLIENT_NOT_VALID(18),
    ENTREPRISE_NOT_VALID(19);
    private int code;
    ErrorCodes(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
