package com.example.nike.ui.product;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0011"}, d2 = {"Lcom/example/nike/ui/product/ProductViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/nike/domain/repository/ProductRepository;", "<init>", "(Lcom/example/nike/domain/repository/ProductRepository;)V", "allProducts", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/example/nike/data/repository/ProductUiModel;", "getAllProducts", "()Lkotlinx/coroutines/flow/StateFlow;", "toggleWishlist", "", "productId", "", "addToCart", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ProductViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.nike.domain.repository.ProductRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.nike.data.repository.ProductUiModel>> allProducts = null;
    
    @javax.inject.Inject()
    public ProductViewModel(@org.jetbrains.annotations.NotNull()
    com.example.nike.domain.repository.ProductRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.nike.data.repository.ProductUiModel>> getAllProducts() {
        return null;
    }
    
    public final void toggleWishlist(int productId) {
    }
    
    public final void addToCart(int productId) {
    }
}