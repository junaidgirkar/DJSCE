from schema_matching import schema_matching

df_pred,df_pred_labels,predicted_pairs = schema_matching("Authors_Table1.csv","Authors_Table2.csv")
print(df_pred)
print("\n")
print(df_pred_labels)
print("\n")
for pair_tuple in predicted_pairs:
    print(pair_tuple)


