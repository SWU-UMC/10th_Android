package com.example.nike.ui.shop;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0012H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/example/nike/ui/shop/SaleFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "_binding", "Lcom/example/nike/databinding/FragmentSaleBinding;", "binding", "getBinding", "()Lcom/example/nike/databinding/FragmentSaleBinding;", "viewModel", "Lcom/example/nike/ui/product/ProductViewModel;", "getViewModel", "()Lcom/example/nike/ui/product/ProductViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "adapter", "Lcom/example/nike/ui/common/ProductGridAdapter;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "Companion", "app_debug"})
public final class SaleFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.nike.databinding.FragmentSaleBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.example.nike.ui.common.ProductGridAdapter adapter;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CATEGORY_SALE = "SALE";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.nike.ui.shop.SaleFragment.Companion Companion = null;
    
    public SaleFragment() {
        super();
    }
    
    private final com.example.nike.databinding.FragmentSaleBinding getBinding() {
        return null;
    }
    
    private final com.example.nike.ui.product.ProductViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/example/nike/ui/shop/SaleFragment$Companion;", "", "<init>", "()V", "CATEGORY_SALE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}