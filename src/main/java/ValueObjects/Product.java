package ValueObjects;

public class Product {
    private String productName;
    private String productIndex;

    private Product(Builder builder) {
        productName = builder.productName;
        productIndex = builder.productIndex;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Product copy) {
        Builder builder = new Builder();
        builder.productName = copy.getProductName();
        builder.productIndex = copy.getProductIndex();
        return builder;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductIndex() {
        return productIndex;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productIndex='" + productIndex + '\'' +
                '}';
    }

    /**
     * {@code Product} builder static inner class.
     */
    public static final class Builder {
        private String productName;
        private String productIndex;

        private Builder() {
        }

        /**
         * Sets the {@code productName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param productName the {@code productName} to set
         * @return a reference to this Builder
         */
        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        /**
         * Sets the {@code productIndex} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param productIndex the {@code productIndex} to set
         * @return a reference to this Builder
         */
        public Builder setProductIndex(String productIndex) {
            this.productIndex = productIndex;
            return this;
        }

        /**
         * Returns a {@code Product} built from the parameters previously set.
         *
         * @return a {@code Product} built with parameters of this {@code Product.Builder}
         */
        public Product build() {
            return new Product(this);
        }
    }
}
