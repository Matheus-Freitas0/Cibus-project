CREATE TABLE forma_pagamento (
    id BIGINT auto_increment PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
);

CREATE TABLE restaurante_forma_pagamento (
    id_restaurante BIGINT NOT NULL,
    id_forma_pagamento BIGINT NOT NULL,
    PRIMARY KEY (id_restaurante, id_forma_pagamento),
    FOREIGN KEY (id_restaurante) REFERENCES restaurante (id),
    FOREIGN KEY (id_forma_pagamento) REFERENCES forma_pagamento (id)
);

INSERT INTO forma_pagamento (descricao) VALUES
('pix'),
('cartao_credito'),
('cartao_debito'),
('vale_refeicao');