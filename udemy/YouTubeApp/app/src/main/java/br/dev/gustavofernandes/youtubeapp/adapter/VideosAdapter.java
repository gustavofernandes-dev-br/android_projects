package br.dev.gustavofernandes.youtubeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.dev.gustavofernandes.youtubeapp.R;
import br.dev.gustavofernandes.youtubeapp.api.TodaRequisicao2;
import br.dev.gustavofernandes.youtubeapp.model.Video;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.MyViewHolder> {

    List<TodaRequisicao2.Item> listaVideos = new ArrayList<>();
    Context context;

    public VideosAdapter(List<TodaRequisicao2.Item> listaVideos, Context context) {
        this.listaVideos = listaVideos;
        this.context = context;
    }

    public void AtualizarLista(List<TodaRequisicao2.Item> listaVideos)
    {
        this.listaVideos = listaVideos;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sView = LayoutInflater
                .from(context)
                .inflate(R.layout.video_item,parent,false);
        return new MyViewHolder(sView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TodaRequisicao2.Item video = listaVideos.get(position);
        holder.textDescricao.setText(video.getSnippet().getDescription());

        String sUrl = video.getSnippet().getThumbnails().getHigh().getURL();
        Picasso.get().load(sUrl).into(holder.imageVideo);


    }

    @Override
    public int getItemCount() {
        return listaVideos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageVideo;
        TextView textDescricao;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageVideo = itemView.findViewById(R.id.image_video);
            textDescricao = itemView.findViewById(R.id.text_view_descricao_video);
        }
    }

}
