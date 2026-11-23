package com.example.bibliotecavirtual.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bibliotecavirtual.model.MiniConto

@Dao
interface MiniContoDAO {

    @Insert
    suspend fun inserir(miniConto: MiniConto)

    @Query("SELECT * FROM mini_conto ORDER BY nome ASC")
    suspend fun listarTodos(): List<MiniConto>

    @Query("SELECT * FROM mini_conto WHERE nome LIKE :filtro ORDER BY nome ASC")
    suspend fun filtrarPorNome(filtro: String): List<MiniConto>

    @Delete
    suspend fun deletar(miniConto: MiniConto)

    @Update
    suspend fun atualizar(miniConto: MiniConto)
}