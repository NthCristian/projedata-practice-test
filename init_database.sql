-- ============================================================================
-- SCHEMA DEFINITION FOR INDUSTRY INVENTORY CONTROL
-- Database: PostgreSQL
-- Language: English (RNF007)
-- ============================================================================

-- ----------------------------------------------------------------------------
-- TABLE: products
-- Description: Stores finished goods information (RF001)
-- ----------------------------------------------------------------------------
CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(150) NOT NULL,
    value NUMERIC(12, 2) NOT NULL CHECK (value >= 0),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_products_code UNIQUE (code)
);

-- Index for search performance
CREATE INDEX idx_products_code ON products (code);

CREATE INDEX idx_products_name ON products (name);

-- ----------------------------------------------------------------------------
-- TABLE: raw_materials
-- Description: Stores raw material/inventory information (RF002)
-- ----------------------------------------------------------------------------
CREATE TABLE raw_materials (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(150) NOT NULL,
    stock_quantity NUMERIC(12, 4) NOT NULL CHECK (stock_quantity >= 0),
    unit_of_measure VARCHAR(20) DEFAULT 'UN', -- e.g., KG, L, UN (Recommended for clarity)
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_raw_materials_code UNIQUE (code)
);

-- Index for search performance
CREATE INDEX idx_raw_materials_code ON raw_materials (code);

-- ----------------------------------------------------------------------------
-- TABLE: product_compositions
-- Description: Associates products with raw materials and defines required quantities (RF003)
--              This represents the Recipe or Bill of Materials (BOM)
-- ----------------------------------------------------------------------------
CREATE TABLE product_compositions (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    raw_material_id BIGINT NOT NULL,
    required_quantity NUMERIC(12, 4) NOT NULL CHECK (required_quantity > 0),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_composition_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    CONSTRAINT fk_composition_raw_material FOREIGN KEY (raw_material_id) REFERENCES raw_materials (id) ON DELETE RESTRICT, -- Prevent deleting material if used in a recipe
    CONSTRAINT uk_product_raw_material UNIQUE (product_id, raw_material_id)
);

-- Indexes for Foreign Keys (Crucial for Join performance in RF004)
CREATE INDEX idx_composition_product_id ON product_compositions (product_id);

CREATE INDEX idx_composition_raw_material_id ON product_compositions (raw_material_id);

-- ----------------------------------------------------------------------------
-- TRIGGER: Update updated_at timestamp automatically
-- ----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_products_updated_at BEFORE UPDATE ON products
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER trg_raw_materials_updated_at BEFORE UPDATE ON raw_materials
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER trg_product_compositions_updated_at BEFORE UPDATE ON product_compositions
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- ----------------------------------------------------------------------------
-- COMMENTS (Documentation)
-- ----------------------------------------------------------------------------
COMMENT ON TABLE products IS 'Finished goods manufactured by the industry';

COMMENT ON COLUMN products.value IS 'Selling price or estimated value of the product';

COMMENT ON TABLE raw_materials IS 'Inputs/Inventory used for production';

COMMENT ON COLUMN raw_materials.stock_quantity IS 'Current available quantity in stock';

COMMENT ON TABLE product_compositions IS 'Recipe definition linking products to required raw materials';

COMMENT ON COLUMN product_compositions.required_quantity IS 'Amount of raw material needed to produce ONE unit of the product';