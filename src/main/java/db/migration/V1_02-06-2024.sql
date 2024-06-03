select constraint_name
from information_schema.constraint_column_usage
where table_name = 'funcionarios_acesso'
  and column_name = 'acesso_id'
  and constraint_name <> 'funcionario_unico_acesso';

alter table funcionarios_acesso drop constraint "ukns6nhh70rxvgdtd2ygm51dsid";